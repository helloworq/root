package com.transform.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = UserMomentInfoRepositry.class, idClass = String.class)
public interface UserMomentInfoRepositry extends JpaRepository<UserMomentInfoRepositry,String> {

}