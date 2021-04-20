package com.transform.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.transform.api.model.dto.UserMomentInfoDTO;
import com.transform.api.model.entiy.UserMomentCollectInfo;
import com.transform.api.model.entiy.UserMomentCommentInfo;
import com.transform.api.model.entiy.UserMomentInfo;
import com.transform.api.model.entiy.UserMomentLikeInfo;
import com.transform.api.service.IBaseInfoService;
import com.transform.api.service.IMomentService;
import com.transform.base.util.ListUtil;
import com.transform.service.dao.UserMomentCollectInfoRepositry;
import com.transform.service.dao.UserMomentCommentInfoRepositry;
import com.transform.service.dao.UserMomentInfoRepositry;
import com.transform.service.dao.UserMomentLikeInfoRepositry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Component
public class UserMomentImpl implements IMomentService {
    @Autowired
    UserMomentInfoRepositry userMomentInfoRepositry;
    @Autowired
    UserMomentLikeInfoRepositry userMomentLikeInfoRepositry;
    @Autowired
    UserMomentCollectInfoRepositry userMomentCollectInfoRepositry;
    @Autowired
    UserMomentCommentInfoRepositry userMomentCommentInfoRepositry;
    @Autowired
    StrogeServiceImpl strogeService;
    @Autowired
    IBaseInfoService baseInfoService;

    /**
     * 上传动态
     *
     * @param userMomentInfoDTO
     * @return
     */
    @Override
    public String uploadMoment(UserMomentInfoDTO userMomentInfoDTO) {
        UserMomentInfo userMomentInfo = new UserMomentInfo();
        //如果是新上传动态，非更新动态则初始化点赞，评论的数据为0
        if (Objects.isNull(userMomentInfo.getId())) {
            userMomentInfoDTO.initCountValue();
        }
        BeanUtils.copyProperties(userMomentInfoDTO, userMomentInfo);
        userMomentInfo.setPicIds(ListUtil.listToString(userMomentInfoDTO.getPicIds()));
        userMomentInfo.setMomentSendTime(new Date());

        String id = userMomentInfoRepositry.save(userMomentInfo).getId();
        return id;
    }

    @Override
    public UserMomentInfoDTO getUserMomentInfo(String id) {
        UserMomentInfoDTO userMomentInfoDTO = new UserMomentInfoDTO();
        BeanUtils.copyProperties(userMomentInfoRepositry.getById(id), userMomentInfoDTO);
        return userMomentInfoDTO;
    }

    @Override
    public List<UserMomentInfoDTO> getAllUserMomentInfo(String uuid) {
        return userMomentInfoRepositry.getByUuidOrderByMomentSendTimeDesc(uuid).stream().map(element -> {
            UserMomentInfoDTO userMomentInfoDTO = new UserMomentInfoDTO();
            BeanUtils.copyProperties(element, userMomentInfoDTO);
            userMomentInfoDTO.setPicIds(ListUtil.stringToList(element.getPicIds()));
            userMomentInfoDTO.setName(baseInfoService.getUserName(element.getUuid()));
            return userMomentInfoDTO;
        }).collect(Collectors.toList());
    }

    /**
     * 为了减少数据库io，把获取好友全部动态的功能放在新方法里执行，同时方便分页
     *
     * @param uuid
     * @return
     */
    @Override
    public List<UserMomentInfoDTO> getAllFriendsMomentInfo(String uuid) {
        return userMomentInfoRepositry.getFriendsMomentsByUserId(uuid).stream().map(elemet -> {
            UserMomentInfoDTO userMomentInfoDTO = new UserMomentInfoDTO();
            BeanUtils.copyProperties(elemet, userMomentInfoDTO);
            userMomentInfoDTO.setPicIds(ListUtil.stringToList(elemet.getPicIds()));
            userMomentInfoDTO.setName(baseInfoService.getUserName(elemet.getUuid()));
            return userMomentInfoDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public String deleteUserMoment(String id) {
        //删除mongo里的文件，再删除oracle里的数据
        UserMomentInfo userMomentInfo = userMomentInfoRepositry.getById(id);
        for (String s : ListUtil.stringToList(userMomentInfo.getPicIds())) {
            strogeService.deleteMongoFile(s);
        }
        userMomentInfoRepositry.deleteById(id);
        return "success";
    }

    @Override
    public void like(String momentId, UserMomentLikeInfo userMomentLikeInfo) {
        //动态赞字段加一,新增like信息
        userMomentLikeInfoRepositry.save(userMomentLikeInfo);
        userMomentInfoRepositry.incLikeCount(momentId);
    }

    @Override
    public void unLike(String momentId, String likeId) {
        //动态赞字段减一,删除like信息
        userMomentLikeInfoRepositry.deleteById(likeId);
        userMomentInfoRepositry.reduceLikeCount(momentId);
    }

    @Override
    public UserMomentLikeInfo getLikeInfo(String momentId, String whoLiked) {
        return userMomentLikeInfoRepositry.findByMomentIdAndWhoLike(momentId, whoLiked);
    }

    @Override
    public void collect(String momentId, UserMomentCollectInfo userMomentCollectInfo) {
        //收藏赞字段加一,删除collect信息
        userMomentCollectInfoRepositry.save(userMomentCollectInfo);
        userMomentInfoRepositry.incCollectCount(momentId);
    }

    @Override
    public void unCollect(String momentId, String collectId) {
        //收藏赞字段减一,新增collect信息
        userMomentCollectInfoRepositry.deleteById(collectId);
        userMomentInfoRepositry.reduceCollectCount(momentId);
    }

    @Override
    public UserMomentCollectInfo getCollectInfo(String momentId, String whoCollected) {
        return userMomentCollectInfoRepositry.findByMomentIdAndAndWhoCollect(momentId, whoCollected);
    }

    @Override
    public void comment(String momentId, UserMomentCommentInfo userMomentCommentInfo) {
        userMomentCommentInfoRepositry.save(userMomentCommentInfo);
        userMomentInfoRepositry.incCommentCount(momentId);
    }

    @Override
    public void deleteComment(String momentId, String whoComment) {
        userMomentInfoRepositry.reduceCommentCount(momentId);
        userMomentCommentInfoRepositry.deleteByMomentIdAndWhoComment(momentId, whoComment);
    }

    @Override
    public List<UserMomentCommentInfo> getComment(String momentId) {
        log.info(JSON.toJSONString(userMomentCommentInfoRepositry.findComment(momentId)));
        return userMomentCommentInfoRepositry.findComment(momentId);
    }

}
