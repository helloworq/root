package com.transform.api.service;

import com.transform.api.model.dto.UserInfoDTO;
import com.transform.api.model.entiy.UserInfo;

import java.util.List;

public interface IFollowService {
    //此接口用来实现有关用户关注的操作，这里传入的参数设置为uuid，即使设置成username但仍然需要查询其uuid
    //关注某人
    String followSomeone(String operationUserUUID, String targetUserUUID);

    //取关某人
    String unFollowSomeone(String operationUserUUID, String targetUserUUID);

    //拉黑某人
    String sendIntoBlackList(String operationUserUUID, String targetUserUUID);

    //取消拉黑某人
    String outFromBlackList(String operationUserUUID, String targetUserUUID);

    //屏蔽某人
    String unFriendSomeone(String operationUserUUID, String targetUserUUID);

    //取消屏蔽某人
    String unUnFriendSomeone(String operationUserUUID, String targetUserUUID);

    //获取用户的好友列表
    List<UserInfoDTO> relationList(String operationUserUUID, Integer relationStatus);

    //获取好友的关注列表
    List<UserInfoDTO> getFans(String operationUserUUID);
}
