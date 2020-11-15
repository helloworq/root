package com.transform.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.transform.api.model.dto.UserMomentInfoDTO;
import com.transform.api.model.entiy.UserMomentCollectInfo;
import com.transform.api.model.entiy.UserMomentCommentInfo;
import com.transform.api.model.entiy.UserMomentInfo;
import com.transform.api.model.entiy.UserMomentLikeInfo;
import com.transform.api.service.IMomentService;
import com.transform.base.util.ListUtil;
import com.transform.service.dao.UserMomentCollectInfoRepositry;
import com.transform.service.dao.UserMomentCommentInfoRepositry;
import com.transform.service.dao.UserMomentInfoRepositry;
import com.transform.service.dao.UserMomentLikeInfoRepositry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        if (Objects.isNull(userMomentInfo.getId())){
            userMomentInfoDTO.initCountValue();
        }
        BeanUtils.copyProperties(userMomentInfoDTO, userMomentInfo);
        userMomentInfo.setPicIds(ListUtil.listToString(userMomentInfoDTO.getPicIds()));
        userMomentInfo.setMomentSendTime(new Date());

        String id=userMomentInfoRepositry.save(userMomentInfo).getId();
        return id;
    }

    @Override
    public UserMomentInfoDTO getUserMomentInfo(String id) {
        UserMomentInfoDTO userMomentInfoDTO = new UserMomentInfoDTO();
        BeanUtils.copyProperties(userMomentInfoRepositry.getById(id), userMomentInfoDTO);
        return userMomentInfoDTO;
    }

    @Override
    public List<UserMomentInfo> getAllUserMomentInfo(String uuid) {
        return userMomentInfoRepositry.getByUuid(uuid);
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
    public String like(UserMomentLikeInfo userMomentLikeInfo) {
        userMomentLikeInfoRepositry.save(userMomentLikeInfo);
        return "success";
    }

    @Override
    public String unLike(String id) {
        userMomentLikeInfoRepositry.deleteById(id);
        return "success";
    }

    @Override
    public UserMomentLikeInfo getLikeInfo(String momentId, String whoLiked) {
        return userMomentLikeInfoRepositry.findByMomentIdAndWhoLike(momentId, whoLiked);
    }

    @Override
    public String collect(UserMomentCollectInfo userMomentCollectInfo) {
        userMomentCollectInfoRepositry.save(userMomentCollectInfo);
        return "success";
    }

    @Override
    public String unCollect(String id) {
        userMomentCollectInfoRepositry.deleteById(id);
        return "success";
    }

    @Override
    public UserMomentCollectInfo getCollectInfo(String momentId, String whoCollected) {
        return userMomentCollectInfoRepositry.findByMomentIdAndAndWhoCollect(momentId, whoCollected);
    }

    @Override
    public String comment(UserMomentCommentInfo userMomentCommentInfo) {
        userMomentCommentInfoRepositry.save(userMomentCommentInfo);
        return "success";
    }

    @Override
    public String deleteComment(String momentId, String whoComment) {
        userMomentCommentInfoRepositry.deleteByMomentIdAndWhoComment(momentId, whoComment);
        return "success";
    }

}
