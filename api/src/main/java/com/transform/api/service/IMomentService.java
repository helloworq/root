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

    List<UserMomentInfoDTO> getAllUserMomentInfo(String uuid);

    List<UserMomentInfoDTO> getAllFriendsMomentInfo(String uuid);

    String deleteUserMoment(String id);

    void like(String momentId, UserMomentLikeInfo userMomentLikeInfo);

    void unLike(String momentId, String likeId);

    UserMomentLikeInfo getLikeInfo(String momentId, String whoLiked);

    void collect(String momentId, UserMomentCollectInfo userMomentCollectInfo);

    void unCollect(String momentId, String id);

    UserMomentCollectInfo getCollectInfo(String momentId, String whoCollected);

    void comment(String momentId, UserMomentCommentInfo userMomentCommentInfo);

    void deleteComment(String momentId, String whoComment);
}
