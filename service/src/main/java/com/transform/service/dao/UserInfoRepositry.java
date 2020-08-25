package com.transform.service.dao;

import com.transform.api.model.entiy.UserAccount;
import com.transform.api.model.entiy.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = UserInfo.class, idClass = String.class)
public interface UserInfoRepositry extends JpaRepository<UserInfo,String> {
    UserInfo findByUuid(String operationUserUUID);

    @Query(value = "select UUID from TB_USERINFO u where u.USER_NAME=?1",nativeQuery = true)
    String findUuid(String operationUsername);
}