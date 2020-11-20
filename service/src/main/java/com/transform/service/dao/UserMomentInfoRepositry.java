package com.transform.service.dao;

import com.transform.api.model.entiy.UserMomentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RepositoryDefinition(domainClass = UserMomentInfo.class, idClass = String.class)
public interface UserMomentInfoRepositry extends JpaRepository<UserMomentInfo, String> {
    //获取用户动态数量
    @Query(value = "select count(*) from TB_USERMOMENTINFO where UUID=?1", nativeQuery = true)
    Integer getUserMomentInfoCount(String operationUserUUID);

    UserMomentInfo getById(String id);

    List<UserMomentInfo> getByUuid(String uuid);

    @Query(value = "select * from TB_USERMOMENTINFO where UUID in (select UUID_USERB from TB_USERRELATION where UUID_USERA=?1) order by MOMENT_SEND_TIME desc ", nativeQuery = true)
    List<UserMomentInfo> getFriendsMomentsByUserId(String uuid);

    @Modifying(clearAutomatically = true) //自动清除实体里保存的数据。
    @Query(value = "update TB_USERMOMENTINFO set LIKE_COUNT = (select LIKE_COUNT-1 from TB_USERMOMENTINFO where ID=?1) where ID=?1" , nativeQuery = true)
    void reduceLikeCount(String momentId);

    @Modifying(clearAutomatically = true) //自动清除实体里保存的数据。
    @Query(value = "update TB_USERMOMENTINFO set LIKE_COUNT = (select LIKE_COUNT+1 from TB_USERMOMENTINFO where ID=?1) where ID=?1" , nativeQuery = true)
    void incLikeCount(String momentId);

    @Modifying(clearAutomatically = true) //自动清除实体里保存的数据。
    @Query(value = "update TB_USERMOMENTINFO set COLLECT_COUNT = (select COLLECT_COUNT-1 from TB_USERMOMENTINFO where ID=?1) where ID=?1" , nativeQuery = true)
    void reduceCollectCount(String momentId);

    @Modifying(clearAutomatically = true) //自动清除实体里保存的数据。
    @Query(value = "update TB_USERMOMENTINFO set COLLECT_COUNT = (select COLLECT_COUNT+1 from TB_USERMOMENTINFO where ID=?1) where ID=?1" , nativeQuery = true)
    void incCollectCount(String momentId);

    @Modifying(clearAutomatically = true) //自动清除实体里保存的数据。
    @Query(value = "update TB_USERMOMENTINFO set COMMENT_COUNT = (select COMMENT_COUNT+1 from TB_USERMOMENTINFO where ID=?1) where ID=?1" , nativeQuery = true)
    void incCommentCount(String momentId);

    @Modifying(clearAutomatically = true) //自动清除实体里保存的数据。
    @Query(value = "update TB_USERMOMENTINFO set COMMENT_COUNT = (select COMMENT_COUNT-1 from TB_USERMOMENTINFO where ID=?1) where ID=?1" , nativeQuery = true)
    void reduceCommentCount(String momentId);
}