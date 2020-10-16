package com.transform.service.dao;

import com.transform.api.model.entiy.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Optional;

@RepositoryDefinition(domainClass = UserInfo.class, idClass = String.class)
public interface UserInfoRepositry extends JpaRepository<UserInfo,String> {
    Optional<UserInfo> findById(String id);

    @Query(value = "select UUID from TB_USERINFO u where u.USER_NAME=?1",nativeQuery = true)
    String findUuid(String Username);
}