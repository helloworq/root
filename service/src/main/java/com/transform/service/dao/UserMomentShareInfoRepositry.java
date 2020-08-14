package com.transform.service.dao;

import com.transform.api.model.entiy.UserMomentShareInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = UserMomentShareInfo.class, idClass = String.class)
public interface UserMomentShareInfoRepositry extends JpaRepository<UserMomentShareInfo,String> {

}