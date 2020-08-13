package com.transform.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = UserMomentShareInfoRepositry.class, idClass = String.class)
public interface UserMomentShareInfoRepositry extends JpaRepository<UserMomentShareInfoRepositry,String> {

}