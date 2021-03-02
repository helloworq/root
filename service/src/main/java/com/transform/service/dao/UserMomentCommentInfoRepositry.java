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

    /**
     * 获取动态对应的评论同时将评论人的id转为name，避免代码层面继续转换
     *
     * @param momemtId
     * @return
     */
    @Query(value =
            "select *\n" +
            "from (\n" +
            "         select TB_USERMONMENTCOMMENTINFO.id,\n" +
            "                comment_content,\n" +
            "                comment_time,\n" +
            "                moment_id,\n" +
            "                (case when WHO_COMMENT = TB_USERINFO.ID then USER_NAME end) WHO_COMMENT\n" +
            "         from TB_USERMONMENTCOMMENTINFO,\n" +
            "              TB_USERINFO\n" +
            "         where MOMENT_ID = ?1)\n" +
            "where WHO_COMMENT is not null\n",nativeQuery = true)
    List<UserMomentCommentInfo> findComment(String momemtId);
}