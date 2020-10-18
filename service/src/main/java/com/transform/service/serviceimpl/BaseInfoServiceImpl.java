package com.transform.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.transform.api.model.dto.UserBaseInfoDTO;
import com.transform.api.model.entiy.UserInfo;
import com.transform.api.service.IBaseInfoService;
import com.transform.service.dao.UserInfoRepositry;
import com.transform.service.dao.UserMomentInfoRepositry;
import com.transform.service.dao.UserRelationRepositry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class BaseInfoServiceImpl implements IBaseInfoService {
    @Autowired
    UserMomentInfoRepositry userMomentInfoRepositry;
    @Autowired
    UserInfoRepositry userInfoRepositry;
    @Autowired
    UserRelationRepositry userRelationRepositry;

    /**
     * 获取关注数
     * @param operationUserUUID
     * @return
     */
    @Override
    public Integer getFollowCount(String operationUserUUID) {
        return userRelationRepositry.getUserFollowCount(operationUserUUID);
    }

    /**
     * 获取粉丝数
     * @param operationUserUUID
     * @return
     */
    @Override
    public Integer getFansCount(String operationUserUUID) {
        return userRelationRepositry.getUserFansCount(operationUserUUID);
    }

    /**
     * 获取动态数
     * @param operationUserUUID
     * @return
     */
    @Override
    public Integer getMomentCount(String operationUserUUID) {
        return userMomentInfoRepositry.getUserMomentInfoCount(operationUserUUID);
    }

    /**
     * 获取基本信息
     * @param operationUserUUID
     * @return
     */
    @Override
    public UserBaseInfoDTO getUserBaseInfo(String operationUserUUID) {
        Optional<UserInfo> userInfo=userInfoRepositry.findById(operationUserUUID);
        UserBaseInfoDTO userBaseInfoDTO=new UserBaseInfoDTO();
        BeanUtils.copyProperties(userInfo,userBaseInfoDTO);
        return userBaseInfoDTO;
    }

    /**
     * 获取用户id
     * @param operationUsername
     * @return
     */
    @Override
    public String getUserId(String operationUsername){
        return userInfoRepositry.findId(operationUsername);
    }

    /**
     * 更新或上传用户信息
     * @param userInfo
     * @return
     */
    @Override
    public String uploadUserInfo(UserInfo userInfo) {
        userInfoRepositry.save(userInfo);
        return "success";
    }

    /**
     * 删除用户信息
     * @param userId
     * @return
     */
    @Override
    public String deleteUserInfo(String userId) {
        userInfoRepositry.deleteById(userId);
        return "success";
    }

    /**
     * 获取全部用户信息
     * @return
     */
    @Override
    public List<UserInfo> getAllUserInfo() {
        return userInfoRepositry.findAll();
    }

    /**
     * 获取用户信息
     * @param userName
     * @return
     */
    @Override
    public UserInfo getUserInfo(String userName) {
        return userInfoRepositry.findByName(userName);
    }

    /**
     * 获取用户全部好友id
     * @param userId
     * @return
     */
    @Override
    public List<String> getFriendsId(String userId) {
        return userRelationRepositry.getFriends(userId);
    }
}
