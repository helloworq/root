package com.transform.api.service;

import com.transform.api.model.dto.UserBaseInfoDTO;
import com.transform.api.model.entiy.UserInfo;

import java.util.List;
import java.util.Optional;

/**
 * 此接口用来获取一些基本信息
 */
public interface IBaseInfoService {
    /**
     * 获取关注人数
     * @return
     */
    Integer getFollowCount(String userId);

    /**
     * 获取粉丝人数
     * @return
     */
    Integer getFansCount(String userId);

    /**
     * 获取动态数
     * @return
     */
    Integer getMomentCount(String userId);

    /**
     * 获取用户的基本信息
     */
    UserBaseInfoDTO getUserBaseInfo(String userId);

    /**
     * 获取用户的uuid
     */
    String getUserId(String userId);

    /**
     * 上传或更新用户信息
     */
    String uploadUserInfo(UserInfo userInfo);

    /**
     * 删除用户信息
     */
    String deleteUserInfo(String userId);

    /**
     * 获取全部用户信息
     */
    List<UserInfo> getAllUserInfo();

    /**
     * 获取指定用户信息
     * @return
     */
    Optional<UserInfo> getUserInfo(String userId);
}
