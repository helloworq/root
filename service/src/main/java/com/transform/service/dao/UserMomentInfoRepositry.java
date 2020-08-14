package com.transform.service.dao;

import com.transform.api.model.entiy.UserMomentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = UserMomentInfo.class, idClass = String.class)
public interface UserMomentInfoRepositry extends JpaRepository<UserMomentInfo,String> {

}