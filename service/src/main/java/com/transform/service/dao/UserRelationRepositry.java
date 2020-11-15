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
    //获取关注用户的全部用户
    @Query(value = "select UUID_USERA from TB_USERRELATION where UUID_USERB=?1 AND RELATION_STATUS=1",nativeQuery = true)
    List<String> getFans(String userId);
    //获取用户关注的用户
    @Query(value = "select UUID_USERB from TB_USERRELATION where UUID_USERA=?1",nativeQuery = true)
    List<String> getFriends(String operationUserUUID);
}
