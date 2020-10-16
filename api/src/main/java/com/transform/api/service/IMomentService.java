package com.transform.api.service;

import com.transform.api.model.dto.UserMomentInfoDTO;
import com.transform.api.model.entiy.UserMomentCollectInfo;
import com.transform.api.model.entiy.UserMomentCommentInfo;
import com.transform.api.model.entiy.UserMomentInfo;
import com.transform.api.model.entiy.UserMomentLikeInfo;

import java.util.List;

public interface IMomentService {

    String uploadMoment(UserMomentInfoDTO userMomentInfo);

    UserMomentInfoDTO getUserMomentInfo(String id);

    List<UserMomentInfo> getAllUserMomentInfo(String uuid);

    String deleteUserMoment(String id);

    String like(UserMomentLikeInfo userMomentLikeInfo);

    String unLike(String id);

    UserMomentLikeInfo getLikeInfo(String momentId,String whoLiked);

    String collect(UserMomentCollectInfo userMomentCollectInfo);

    String unCollect(String id);

    UserMomentCollectInfo getCollectInfo(String momentId,String whoCollected);

    String comment(UserMomentCommentInfo userMomentCommentInfo);

    String deleteComment(String momentId,String whoComment);
}
