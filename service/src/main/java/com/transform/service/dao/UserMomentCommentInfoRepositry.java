package com.transform.service.dao;

import com.transform.api.model.entiy.UserMomentCommentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass = UserMomentCommentInfo.class, idClass = String.class)
public interface UserMomentCommentInfoRepositry extends JpaRepository<UserMomentCommentInfo,String> {
    void deleteByMomentIdAndWhoComment(String momentId,String whoComment);
}