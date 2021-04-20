package com.transform.service.dao;

import com.transform.api.model.entiy.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RepositoryDefinition(domainClass = UserInfo.class, idClass = String.class)
public interface UserInfoRepositry extends JpaRepository<UserInfo, String> {
    @Query(value = "select * from TB_USERINFO u where u.USER_NAME=?1", nativeQuery = true)
    UserInfo findByName(String name);

    @Query(value = "select id from TB_USERINFO u where u.USER_NAME=?1", nativeQuery = true)
    String findId(String Username);

    @Query(value = "select USER_NAME from TB_USERINFO u where u.ID=?1", nativeQuery = true)
    String findName(String UserId);

    //获取关注用户的全部用户
    //@Query(value = "select UUID_USERA from TB_USERRELATION where UUID_USERB=?1 AND RELATION_STATUS=1",nativeQuery = true)
    @Query(value = "select * from TB_USERINFO where ID in (select UUID_USERA from TB_USERRELATION where UUID_USERB=?1 AND RELATION_STATUS=1)", nativeQuery = true)
    List<UserInfo> getFans(String userId);

    //获取用户关注/屏蔽/拉黑的用户(根据传入用户id查找其朋友的信息)
    //@Query(value = "select UUID_USERB from TB_USERRELATION where UUID_USERA=?1",nativeQuery = true)
    @Query(value = "select * from TB_USERINFO where ID in (select UUID_USERB from TB_USERRELATION where UUID_USERA=?1 and RELATION_STATUS=?2)", nativeQuery = true)
    List<UserInfo> relationList(String operationUserUUID, Integer relationStatus);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE TB_USERINFO set USER_HEAD_URL=?1 where USER_NAME=?2", nativeQuery = true)
    void updateHeadIcon(String headIcon, String userName);
}