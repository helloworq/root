package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.transform.api.model.dto.UserInfoDTO;
import com.transform.api.model.dto.UserMomentInfoDTO;
import com.transform.api.model.dto.custom.Message;
import com.transform.api.model.entiy.*;
import com.transform.api.model.entiy.mongo.ResourceInfo;
import com.transform.api.service.IBaseInfoService;
import com.transform.api.service.IFollowService;
import com.transform.api.service.IMomentService;
import com.transform.api.service.IStrogeService;
import com.transform.base.response.ResponseData;
import com.transform.base.response.ResponseUtil;
import com.transform.base.util.FileUtil;
import com.transform.base.util.ListUtil;
import com.transform.web.util.AsyncUtil;
import com.transform.web.util.MyIOUtil;
import com.transform.web.util.WebTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 文件上传
 */
@Api(description = "上传动态控制器")
@RestController
@RequestMapping("/v1/rest")
public class MomentController {
    @Reference
    IStrogeService strogeService;
    @Reference
    IMomentService momentService;
    @Reference
    IBaseInfoService baseInfoService;
    @Reference
    IFollowService followService;

    @Autowired
    MyIOUtil myIOUtil;
    @Autowired
    WebTools tools;
    @Autowired
    AsyncUtil asyncUtil;

    /**
     * 上传文件以及文件信息到mongo和oracle，dubbo下文件无法序列化导致无法传输，
     * 故采取先上传到临时目录的方法
     *
     * @param list
     * @param userMomentInfoDTO
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "更新/上传动态")
    @PostMapping(value = "/fileUpload")
    public ResponseData fileUpload(@ApiParam("多选") @RequestParam(value = "file") MultipartFile[] list,
                                   @ApiParam("参数(动态内容，是否编辑，设备名必传)") @Validated UserMomentInfoDTO userMomentInfoDTO,
                                   HttpServletRequest request) throws InterruptedException {
        System.out.println(tools.getCookie(request.getCookies(), "userName"));
        if (list.length == 0 || null == list)
            return null;
        //判断是更新请求还是上传请求,是更新请求则删除已存信息
        if (null != userMomentInfoDTO.getId()) {
            //删除已存文件
            UserMomentInfoDTO momentInfo = momentService.getUserMomentInfo(userMomentInfoDTO.getId());
            List<String> picIds = momentInfo.getPicIds();
            for (String s : picIds) {
                strogeService.deleteMongoFile(s);
            }
        }
        List<String> imageIds = new ArrayList<>();
        for (MultipartFile file : list) {
            imageIds.add(myIOUtil.saveToTempPath(file));//存入到Mongo
        }
        userMomentInfoDTO.setPicIds(imageIds);
        userMomentInfoDTO.setUuid(baseInfoService.getUserId(tools.getCookie(request.getCookies(), "userName")));

        //上传动态到oracle
        String momentId = momentService.uploadMoment(userMomentInfoDTO);
        //上传完成之后将消息推送给朋友
        String userId = baseInfoService.getUserId(tools.getCookie(request.getCookies(), "userName"));
        if (Objects.nonNull(userId)) {
            Message message = new Message(momentId, new Date().toString(), userId);
            List<String> friendsList = baseInfoService.getFriendsId(userId).stream().map(UserInfo::getId).collect(Collectors.toList());
            asyncUtil.pushMoment(friendsList.toArray(new String[friendsList.size()]), message);
        }
        return ResponseUtil.success("上传成功！");
    }

    /**
     * 获取全部动态
     *
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "获取全部动态")
    @GetMapping(value = "/getAllMonment")
    public ResponseData getAllMonment(HttpServletRequest request) {
        String userId = baseInfoService.getUserId(tools.getCookie(request.getCookies(), "userName"));
        return ResponseUtil.success(momentService.getAllUserMomentInfo(userId));
    }

    /**
     * 根据id删除动态
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除动态")
    @DeleteMapping(value = "/deleteUserMoment")
    public ResponseData deleteUserMoment(@RequestParam("id") String id) {
        return ResponseUtil.success(momentService.deleteUserMoment(id));
    }

    /**
     * 根据id获取某一条动态信息,此时picIds需要换成链接
     *
     * @param id
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "根据id获取某一条动态信息")
    @GetMapping(value = "/getMomentInfo")
    public ResponseData getMomentInfo(@RequestParam("id") String id) throws IOException {
        UserMomentInfoDTO userMomentInfoDTO = momentService.getUserMomentInfo(id);
        //获取动态内的文件id
        List<String> picIds = userMomentInfoDTO.getPicIds();
        //转换完成的链接写入结果集
        userMomentInfoDTO.setPicIds(myIOUtil.picIdsToLinks(picIds));
        return ResponseUtil.success(userMomentInfoDTO);
    }

    /**
     * 关于点赞收藏评论转发的接口，点赞
     * 点赞的时候由两种情况，第一种是点过赞了，这时得取消点赞，第二种是第一次点赞
     */
    @ApiOperation(value = "赞/取消赞")
    @GetMapping(value = "/like")
    public ResponseData like(@RequestParam("MomentId") String momentId, HttpServletRequest request) {
        //判断当前用户是初次点赞还是取消点赞
        String whoLiked = baseInfoService.getUserId(tools.getCookie(request.getCookies(), "userName"));
        UserMomentLikeInfo userMomentLikeInfo = momentService.getLikeInfo(momentId, whoLiked);
        //更改逻辑，将逻辑全部放在数据库  删除赞记录，动态赞点击数减一
        if (null != userMomentLikeInfo) {
            momentService.unLike(momentId, userMomentLikeInfo.getId());
        } else {                         //赞
            UserMomentLikeInfo saveUserMomentLikeInfo = new UserMomentLikeInfo();
            saveUserMomentLikeInfo.setLikeTime(new Date());
            saveUserMomentLikeInfo.setMomentId(momentId);
            saveUserMomentLikeInfo.setWhoLike(whoLiked);
            momentService.like(momentId, saveUserMomentLikeInfo);
        }
        return ResponseUtil.success("success");
    }

    /**
     * 收藏，和点赞差不多
     */
    @ApiOperation(value = "收藏/取消收藏")
    @GetMapping(value = "/collect")
    public ResponseData collect(@RequestParam("MomentId") String momentId, HttpServletRequest request) {
        String whoCollected = baseInfoService.getUserId(tools.getCookie(request.getCookies(), "userName"));
        UserMomentCollectInfo userMomentCollectInfo = momentService.getCollectInfo(momentId, whoCollected);
        //更改逻辑，将逻辑全部放在数据库  删除收藏记录，动态赞点击数加一
        if (null != userMomentCollectInfo) {
            momentService.unCollect(momentId, userMomentCollectInfo.getId());
        } else {
            UserMomentCollectInfo saveUserMomentCollectInfo = new UserMomentCollectInfo();
            saveUserMomentCollectInfo.setCollectTime(new Date());
            saveUserMomentCollectInfo.setMomentId(momentId);
            saveUserMomentCollectInfo.setWhoCollect(whoCollected);
            momentService.collect(momentId, saveUserMomentCollectInfo);
        }
        return ResponseUtil.success("success");
    }

    /**
     * 评论 字段 id,momentId,commentContent,whoComment,commentTime
     */
    @ApiOperation(value = "评论")
    @GetMapping(value = "/sendComment")
    public ResponseData sendComment(@RequestParam(value = "text") String text,
                                    @RequestParam(value = "momentId") String momentId,
                                    HttpServletRequest request) {
        String whoComment = baseInfoService.getUserId(tools.getCookie(request.getCookies(), "userName"));
        UserMomentCommentInfo userMomentCommentInfo = new UserMomentCommentInfo();
        userMomentCommentInfo.setMomentId(momentId);
        userMomentCommentInfo.setWhoComment(whoComment);
        userMomentCommentInfo.setCommentContent(text);
        userMomentCommentInfo.setCommentTime(new Date());
        momentService.comment(momentId, userMomentCommentInfo);
        //评论完成之后，动态表的评论字段需自增
        return ResponseUtil.success("success");
    }

    /**
     * 删除评论
     *
     * @param momentId
     * @param request
     * @return
     */
    @ApiOperation(value = "删除评论")
    @GetMapping(value = "/deleteComment")
    public ResponseData deleteComment(@RequestParam(value = "momentId") String momentId,
                                      HttpServletRequest request) {
        //评论完成之后，动态表的评论字段需自增
        String whoComment = baseInfoService.getUserId(tools.getCookie(request.getCookies(), "userName"));
        momentService.deleteComment(momentId, whoComment);
        return ResponseUtil.success("success");
    }

    /**
     * 获取主页信息
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "获取个人主页信息")
    @GetMapping(value = "/getMainPageInfo")
    public ResponseData getMainPageInfo(HttpServletRequest request) {
        //根据用户名获取主页信息
        String userName = tools.getCookie(request.getCookies(), "userName");
        String userId = baseInfoService.getUserId(userName);
        //获取关注用户的信息
        List<UserInfoDTO> friendsList = followService.getFriendsList(userId);

        //获取粉丝的信息
        List<UserInfoDTO> fansList = followService.getFriendsList(userId);

        //获取个人全部动态，UserMomentInfoDTO和UserMomentInfo的picIds格式不一样，在流里也需要处理
        List<UserMomentInfoDTO> userMomentInfoList = momentService.getAllUserMomentInfo(userId);
        userMomentInfoList.stream().forEach(element -> {
            try {
                element.setPicIds(myIOUtil.picIdsToLinks(element.getPicIds()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        JSONObject object = new JSONObject();
        object.put("friendsList", friendsList);
        object.put("fansList", fansList);
        object.put("moments", userMomentInfoList);
        return ResponseUtil.success(object);
    }

    @ApiOperation(value = "获取好友动态信息")
    @GetMapping(value = "/getFriendMoment")
    public ResponseData getFriendMoment(HttpServletRequest request) {
        //根据用户名获取主页信息
        String userName = tools.getCookie(request.getCookies(), "userName");
        String userId = baseInfoService.getUserId(userName);

        //获取全部动态，UserMomentInfoDTO和UserMomentInfo的picIds格式不一样，在流里也需要处理
        //根据好友列表里的好友id查询好友全部动态然后依据时间排序排序（等数据量大了的时候可以考虑将逻辑在数据库里完成以减少io）
        List<UserMomentInfoDTO> userMomentInfoList = momentService.getAllFriendsMomentInfo(userId);
        userMomentInfoList.stream().forEach(element -> {
            try {
                element.setPicIds(myIOUtil.picIdsToLinks(element.getPicIds()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        JSONObject object = new JSONObject();
        object.put("moments", userMomentInfoList);
        return ResponseUtil.success(object);
    }

    @ApiOperation(value = "根据动态id获取评论")
    @GetMapping(value = "/getComment")
    public ResponseData getComment(@RequestParam("momentId") String momentId) {
        return ResponseUtil.success(momentService.getComment(momentId));
    }
}