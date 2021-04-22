package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.model.dto.UserInfoDTO;
import com.transform.api.model.entiy.UserInfo;
import com.transform.api.service.IBaseInfoService;
import com.transform.api.service.IFollowService;
import com.transform.base.constant.Global_Constant;
import com.transform.base.response.ResponseData;
import com.transform.base.response.ResponseUtil;
import com.transform.web.util.MyIOUtil;
import com.transform.web.util.WebTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 此类提供关于用户关注的功能
 */
@Api(description = "用户关注控制器")
@RestController
@RequestMapping("v1/rest/")
public class FollowController {

    @Reference
    IBaseInfoService baseInfoService;

    @Reference
    IFollowService followService;

    @Autowired
    MyIOUtil myIOUtil;

    @Autowired
    WebTools tools;

    @ApiOperation(value = "获取基础信息")
    @GetMapping("getbaseinfo")
    public ResponseData getbaseinfo(@RequestParam("userId") String userId) {
        return ResponseUtil.success(baseInfoService.getUserBaseInfo(userId));
    }

    @ApiOperation(value = "关注")
    @GetMapping("relation/follow")
    public ResponseData follow(@RequestParam("targetUserUUID") String targetUserUUID,
                               HttpServletRequest request) {
        String operationUserName = tools.getCookie(request.getCookies(), "userName");
        String operationUserUUID = baseInfoService.getUserId(operationUserName);
        return ResponseUtil.success(followService.followSomeone(operationUserUUID, targetUserUUID));
    }

    @ApiOperation(value = "取消关注")
    @GetMapping("relation/unfollow")
    public ResponseData unfollow(@RequestParam("targetUserUUID") String targetUserUUID,
                                 HttpServletRequest request) {
        String operationUserName = tools.getCookie(request.getCookies(), "userName");
        String operationUserUUID = baseInfoService.getUserId(operationUserName);
        return ResponseUtil.success(followService.unFollowSomeone(operationUserUUID, targetUserUUID));
    }

    @ApiOperation(value = "处理关系")
    @GetMapping("relation/handleRelation")
    public ResponseData handleRelation(@RequestParam("targetUserUUID") String targetUserUUID,
                                       @RequestParam("relationStatus") Integer relationStatus,
                                       HttpServletRequest request) {
        String operationUserName = tools.getCookie(request.getCookies(), "userName");
        String operationUserUUID = baseInfoService.getUserId(operationUserName);
        return ResponseUtil.success(followService.handleRelation(operationUserUUID, targetUserUUID, relationStatus));
    }

    @ApiOperation(value = "获取关系列表")
    @GetMapping("relation/get/{relationList}")
    public ResponseData getFriendsList(@RequestParam("operationUserUUID") String operationUserUUID,
                                       @PathVariable String relationList) throws IOException {

        Integer relationStatus = Global_Constant.SOCIAL_RELATION_URL_MAP.get(relationList);

        if (relationStatus == 2) {
            return ResponseUtil.fail("empty");
        }

        List<UserInfoDTO> relationSocialList = followService.relationList(operationUserUUID, relationStatus);
        myIOUtil.picIdToLinkList(relationSocialList, "userHeadUrl");

        return ResponseUtil.success(relationSocialList);
    }

    @ApiOperation(value = "获取粉丝列表")
    @GetMapping("relation/get/FansList")
    public ResponseData getFans(@RequestParam("operationUserUUID") String operationUserUUID) throws IOException {

        List<UserInfoDTO> relationfansList = followService.getFans(operationUserUUID);
        myIOUtil.picIdToLinkList(relationfansList, "userHeadUrl");

        return ResponseUtil.success(relationfansList);
    }

    /**
     * 获取全部用户信息
     *
     * @return
     */
    @ApiOperation(value = "获取全部用户信息")
    @GetMapping("relation/get/friendSquareList")
    public ResponseData getAllUserInfo() throws IOException {

        List<UserInfo> allUserInfo = baseInfoService.getAllUserInfo();
        myIOUtil.picIdToLinkList(allUserInfo, "userHeadUrl");

        return ResponseUtil.success(allUserInfo);
    }
}
