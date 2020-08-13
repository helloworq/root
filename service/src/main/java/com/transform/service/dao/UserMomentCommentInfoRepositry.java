package com.transform.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass = UserMomentCommentInfoRepositry.class, idClass = String.class)
public interface UserMomentCommentInfoRepositry extends JpaRepository<UserMomentCommentInfoRepositry,String> {

}