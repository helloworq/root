package com.transform.service.dao;

import com.transform.api.model.entiy.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Optional;

@RepositoryDefinition(domainClass = UserInfo.class, idClass = String.class)
public interface UserInfoRepositry extends JpaRepository<UserInfo,String> {
    @Query(value = "select * from TB_USERINFO u where u.USER_NAME=?1",nativeQuery = true)
    UserInfo findByName(String name);

    @Query(value = "select id from TB_USERINFO u where u.USER_NAME=?1",nativeQuery = true)
    String findId(String Username);

    @Query(value = "select USER_NAME from TB_USERINFO u where u.ID=?1",nativeQuery = true)
    String findName(String UserId);
}