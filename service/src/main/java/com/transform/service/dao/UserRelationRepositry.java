package com.transform.service.dao;

import com.transform.api.model.entiy.UserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = UserRelation.class, idClass = String.class)
public interface UserRelationRepositry extends JpaRepository<UserRelation,String> {

    //查询用户之间的关系
    @Query(value = "select RELATION_STATUS from TB_USERRELATION where UUID_USERA=?1 and UUID_USERB=?2",nativeQuery = true)
    String findTargetRelationUUID(String operationUserUUID,String targetUserUUID);
    //拉黑某人
    @Query(value = "update TB_USERRELATION set RELATION_STATUS=3 where UUID_USERA=?1 and UUID_USERB=?2",nativeQuery = true)
    String sendIntoBlackList(String operationUserUUID,String targetUserUUID);
    //取消拉黑某人
    @Query(value = "update TB_USERRELATION set RELATION_STATUS=1 where UUID_USERA=?1 and UUID_USERB=?2",nativeQuery = true)
    String outFromBlackList(String operationUserUUID,String targetUserUUID);
    //屏蔽某人
    @Query(value = "update TB_USERRELATION set RELATION_STATUS=2 where UUID_USERA=?1 and UUID_USERB=?2",nativeQuery = true)
    String unFriendSomeone(String operationUserUUID,String targetUserUUID);
    //取消屏蔽某人
    @Query(value = "update TB_USERRELATION set RELATION_STATUS=1 where UUID_USERA=?1 and UUID_USERB=?2",nativeQuery = true)
    String unUnFriendSomeone(String operationUserUUID,String targetUserUUID);
    //获取用户的关注人数
    @Query(value = "select count(*) from TB_USERRELATION where UUID_USERA=?1",nativeQuery = true)
    Integer getUserFollowCount(String operationUserUUID);
    //获取用户粉丝数
    @Query(value = "select count(*) from TB_USERRELATION where UUID_USERB=?1",nativeQuery = true)
    Integer getUserFansCount(String operationUserUUID);
    //获取用户全部好友
    @Query(value = "select UUID_USERB from TB_USERRELATION where UUID_USERA=?1 AND RELATION_STATUS=1",nativeQuery = true)
    List<String> getFriends(String userId);
}
