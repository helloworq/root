package com.transform.service.dao;

import com.transform.api.model.entiy.UserMomentCommentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;


@RepositoryDefinition(domainClass = UserMomentCommentInfo.class, idClass = String.class)
public interface UserMomentCommentInfoRepositry extends JpaRepository<UserMomentCommentInfo,String> {
    void deleteByMomentIdAndWhoComment(String momentId,String whoComment);

    @Query(value = "select * from TB_USERMONMENTCOMMENTINFO where MOMENT_ID=?1",nativeQuery = true)
    List<UserMomentCommentInfo> findComment(String momemtId);
}