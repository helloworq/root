package com.transform.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.transform.api.model.dto.UserInfoDTO;
import com.transform.api.model.entiy.UserRelation;
import com.transform.api.service.IFollowService;
import com.transform.service.dao.UserInfoRepositry;
import com.transform.service.dao.UserRelationRepositry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class followServiceImpl implements IFollowService {
    @Autowired
    UserRelationRepositry userRelationRepositry;
    @Autowired
    UserInfoRepositry userInfoRepositry;

    /**
     * 关注用户功能，用户之间的关系有四种，无关系-0，关注-1，屏蔽-2，拉黑-3，这三种状态是互斥的，也就是说不能同时为true。所以当有
     * 对用户进行某些操作时只需要将状态都置为false再将目标操作置为true，当都为false时表明未关注用户,
     * 取消屏蔽和取消拉黑都是恢复到朋友状态
     *
     * @param operationUserUUID
     * @param targetUserUUID
     * @return
     */
    @Override
    public String followSomeone(String operationUserUUID, String targetUserUUID) {
        userRelationRepositry.save(new UserRelation(operationUserUUID, targetUserUUID, 1, new Date()));
        return "success";
    }

    @Override
    public String unFollowSomeone(String operationUserUUID, String targetUserUUID) {
        //找到目标关系数据，再delete
        userRelationRepositry.deleteByUuidUserAAndUuidUserB(operationUserUUID, targetUserUUID);
        return "success";
    }

    @Override
    public String handleRelation(String operationUserUUID, String targetUserUUID, Integer relationStatus) {
        //无关系-0，关注-1，屏蔽-2，拉黑-3      取消屏蔽和取消拉黑都是恢复到朋友状态
        userRelationRepositry.handleRelation(operationUserUUID, targetUserUUID, relationStatus);
        return "success";
    }

    @Override
    public List<UserInfoDTO> relationList(String operationUserUUID, Integer relationStatus) {
        return userInfoRepositry.relationList(operationUserUUID, relationStatus).stream()
                .map(elemet -> {
                    UserInfoDTO userInfoDTO = new UserInfoDTO();
                    BeanUtils.copyProperties(elemet, userInfoDTO);
                    return userInfoDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public List<UserInfoDTO> getFans(String operationUserUUID) {
        return userInfoRepositry.getFans(operationUserUUID).stream()
                .map(elemet -> {
                    UserInfoDTO userInfoDTO = new UserInfoDTO();
                    BeanUtils.copyProperties(elemet, userInfoDTO);
                    return userInfoDTO;
                }).collect(Collectors.toList());
    }
}
