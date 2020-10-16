package com.transform.service.dao;

import com.transform.api.model.entiy.UserMomentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = UserMomentInfo.class, idClass = String.class)
public interface UserMomentInfoRepositry extends JpaRepository<UserMomentInfo,String> {
    //获取用户动态数量
    @Query(value = "select count(*) from TB_USERMOMENTINFO where UUID=?1",nativeQuery = true)
    Integer getUserMomentInfoCount(String operationUserUUID);

    UserMomentInfo getById(String id);

    List<UserMomentInfo> getByUuid(String uuid);
}