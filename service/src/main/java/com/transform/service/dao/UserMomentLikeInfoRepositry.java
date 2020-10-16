package com.transform.service.dao;

import com.transform.api.model.entiy.UserMomentLikeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass = UserMomentLikeInfo.class, idClass = String.class)
public interface UserMomentLikeInfoRepositry extends JpaRepository<UserMomentLikeInfo,String> {
    @Query(value = "select * from TB_USERMONMENTLIKEINFO t where t.MOMENT_ID=?1 and t.WHO_LIKE=?2",nativeQuery = true)
    UserMomentLikeInfo findByMomentIdAndWhoLike(String momentId,String whoLike);
}