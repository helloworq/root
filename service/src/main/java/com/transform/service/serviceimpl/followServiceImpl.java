package com.transform.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.transform.api.model.dto.UserInfoDTO;
import com.transform.api.model.entiy.UserInfo;
import com.transform.api.model.entiy.UserRelation;
import com.transform.api.service.IBaseInfoService;
import com.transform.api.service.IFollowService;
import com.transform.service.dao.UserRelationRepositry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class followServiceImpl implements IFollowService {
    @Autowired
    UserRelationRepositry userRelationRepositry;
    /**
     * 关注用户功能，用户之间的关系有三种，关注，拉黑，屏蔽，这三种状态是互斥的，也就是说不能同时为true。所以当有
     * 对用户进行某些操作时只需要将状态都置为false再将目标操作置为true，当都为false时表明未关注用户,
     * @param operationUserUUID
     * @param targetUserUUID
     * @return
     */
    @Override
    public String followSomeone(String operationUserUUID, String targetUserUUID) {
        userRelationRepositry.save(new UserRelation(operationUserUUID,targetUserUUID,1,new Date()));
        return "success";
    }

    @Override
    public String unFollowSomeone(String operationUserUUID, String targetUserUUID) {
        //找到目标关系数据，再delete
        userRelationRepositry.deleteById(userRelationRepositry.findTargetRelationUUID(operationUserUUID, targetUserUUID));
        return "success";
    }

    @Override
    public String sendIntoBlackList(String operationUserUUID, String targetUserUUID) {
        //拉黑某人，只允许好友状态或者屏蔽状态拉黑，不允许拉黑路人
        userRelationRepositry.sendIntoBlackList(operationUserUUID, targetUserUUID);
        return "success";
    }

    @Override
    public String outFromBlackList(String operationUserUUID, String targetUserUUID) {
        userRelationRepositry.outFromBlackList(operationUserUUID, targetUserUUID);
        return "success";
    }

    @Override
    public String unFriendSomeone(String operationUserUUID, String targetUserUUID) {
        userRelationRepositry.unFriendSomeone(operationUserUUID, targetUserUUID);
        return "success";
    }

    @Override
    public String unUnFriendSomeone(String operationUserUUID, String targetUserUUID) {
        userRelationRepositry.unUnFriendSomeone(operationUserUUID, targetUserUUID);
        return "success";
    }

    @Override
    public List<UserInfoDTO> getFriendsList(String operationUserUUID) {
        return userRelationRepositry.getFriends(operationUserUUID)
                .stream()
                .map(elemet->{
                    UserInfoDTO userInfoDTO=new UserInfoDTO();
                    BeanUtils.copyProperties(elemet,userInfoDTO);
                    return userInfoDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public List<UserInfoDTO> getFans(String operationUserUUID) {
        return userRelationRepositry.getFans(operationUserUUID)
                .stream()
                .map(elemet->{
                    UserInfoDTO userInfoDTO=new UserInfoDTO();
                    BeanUtils.copyProperties(elemet,userInfoDTO);
                    return userInfoDTO;
                }).collect(Collectors.toList());
    }
}
