package com.transform.service.dao;

import com.transform.api.model.entiy.UserMomentLikeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass = UserMomentLikeInfo.class, idClass = String.class)
public interface UserMomentLikeInfoRepositry extends JpaRepository<UserMomentLikeInfo,String> {

}