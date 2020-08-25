package com.transform.api.service;

import com.transform.api.model.dto.UserBaseInfoDTO;
import com.transform.api.model.entiy.UserInfo;

/**
 * 此接口用来获取一些基本信息
 */
public interface IBaseInfoService {
    /**
     * 获取关注人数
     * @return
     */
    Integer getFollowCount(String operationUserUUID);

    /**
     * 获取粉丝人数
     * @return
     */
    Integer getFansCount(String operationUserUUID);

    /**
     * 获取动态数
     * @return
     */
    Integer getMomentCount(String operationUserUUID);

    /**
     * 获取用户的基本信息
     */
    UserBaseInfoDTO getUserBaseInfo(String operationUserUUID);

    /**
     * 获取用户的uuid
     */
    String getUserUUID(String operationUsername);
}
