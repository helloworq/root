package com.transform.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass = UserMomentLikeInfoRepositry.class, idClass = String.class)
public interface UserMomentLikeInfoRepositry extends JpaRepository<UserMomentLikeInfoRepositry,String> {

}