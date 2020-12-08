package com.transform.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.transform.api.model.dto.UserBaseInfoDTO;
import com.transform.api.model.entiy.UserInfo;
import com.transform.api.service.IBaseInfoService;
import com.transform.service.dao.UserInfoRepositry;
import com.transform.service.dao.UserMomentInfoRepositry;
import com.transform.service.dao.UserRelationRepositry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
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
     * 获取用户name
     * @param operationUserId
     * @return
     */
    @Override
    public String getUserName(String operationUserId){
        return userInfoRepositry.findName(operationUserId);
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
        log.info(userName);
        return userInfoRepositry.findByName(userName);
    }

    /**
     * 获取用户全部好友id
     * @param userId
     * @return
     */
    @Override
    public List<UserInfo> getFriendsId(String userId) {
        return userInfoRepositry.getFriends(userId);
    }

    /**
     * 更新用户头像信息
     * @param picId
     * @return
     */
    @Override
    public void updateHeadIcon(String picId,String userName) {
        userInfoRepositry.updateHeadIcon(picId, userName);
    }
}
