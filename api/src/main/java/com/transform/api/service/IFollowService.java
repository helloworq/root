package com.transform.api.service;

import com.transform.api.model.dto.UserInfoDTO;

import java.util.List;

public interface IFollowService {
    //此接口用来实现有关用户关注的操作，这里传入的参数设置为uuid，即使设置成username但仍然需要查询其uuid
    //关注某人
    String followSomeone(String operationUserUUID, String targetUserUUID);

    String unFollowSomeone(String operationUserUUID, String targetUserUUID);

    //无关系-0，关注-1，屏蔽-2，拉黑-3                  取消屏蔽和取消拉黑都是恢复到朋友状态
    String handleRelation(String operationUserUUID, String targetUserUUID, Integer relationStatus);

    //获取用户的好友列表
    List<UserInfoDTO> relationList(String operationUserUUID, Integer relationStatus);

    //获取好友的关注列表
    List<UserInfoDTO> getFans(String operationUserUUID);
}
