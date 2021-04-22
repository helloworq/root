package com.transform.service.dao;

import com.transform.api.model.entiy.UserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryDefinition(domainClass = UserRelation.class, idClass = String.class)
public interface UserRelationRepositry extends JpaRepository<UserRelation, String> {

    @Transactional
    @Modifying
    String deleteByUuidUserAAndUuidUserB(String operationUserUUID, String targetUserUUID);

    //无关系-0，关注-1，屏蔽-2，拉黑-3                  取消屏蔽和取消拉黑都是恢复到朋友状态
    @Query(value = "update TB_USERRELATION set RELATION_STATUS=?3 where UUID_USERA=?1 and UUID_USERB=?2", nativeQuery = true)
    String handleRelation(String operationUserUUID, String targetUserUUID, Integer relationStatus);

    //查找某两位用户的共同好友
    @Query(value =
            "select USER_NAME\n" +
                    "from TB_USERINFO\n" +
                    "where ID in (\n" +
                    "    select TB_USERRELATION.UUID_USERB\n" +
                    "    from TB_USERRELATION\n" +
                    "    where UUID_USERA = (select TB_USERINFO.ID\n" +
                    "                        from TB_USERINFO\n" +
                    "                        where USER_NAME = ?1)\n" +
                    "      and UUID_USERB in (\n" +
                    "        select UUID_USERB\n" +
                    "        from TB_USERRELATION\n" +
                    "        where UUID_USERA = (select TB_USERINFO.ID\n" +
                    "                            from TB_USERINFO\n" +
                    "                            where USER_NAME = ?2))\n" +
                    ")", nativeQuery = true)
    List<String> getCommonFriends(String userA, String userB);
}
