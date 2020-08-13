package com.transform.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = UserMomentCollectInfoRepositry.class, idClass = String.class)
public interface UserMomentCollectInfoRepositry extends JpaRepository<UserMomentCollectInfoRepositry,String> {

}