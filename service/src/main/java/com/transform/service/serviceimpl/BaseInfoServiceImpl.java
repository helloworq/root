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

@Service
@Component
public class BaseInfoServiceImpl implements IBaseInfoService {
    @Autowired
    UserMomentInfoRepositry userMomentInfoRepositry;
    @Autowired
    UserInfoRepositry userInfoRepositry;
    @Autowired
    UserRelationRepositry userRelationRepositry;

    @Override
    public Integer getFollowCount(String operationUserUUID) {
        return userRelationRepositry.getUserFollowCount(operationUserUUID);
    }

    @Override
    public Integer getFansCount(String operationUserUUID) {
        return userRelationRepositry.getUserFansCount(operationUserUUID);
    }

    @Override
    public Integer getMomentCount(String operationUserUUID) {
        return userMomentInfoRepositry.getUserMomentInfoCount(operationUserUUID);
    }

    @Override
    public UserBaseInfoDTO getUserBaseInfo(String operationUserUUID) {
        UserInfo userInfo=userInfoRepositry.findByUuid(operationUserUUID);
        UserBaseInfoDTO userBaseInfoDTO=new UserBaseInfoDTO();
        BeanUtils.copyProperties(userInfo,userBaseInfoDTO);
        return userBaseInfoDTO;
    }

}
