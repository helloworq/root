package com.transform.service.dao;

import com.transform.api.model.entiy.UserInfo;
import com.transform.api.model.entiy.UserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = UserRelation.class, idClass = String.class)
public interface UserRelationRepositry extends JpaRepository<UserRelation,String> {

    //查询用户之间的关系
    @Query(value = "select RELATION_STATUS from TB_USERRELATION where UUID_USERA=?1 and UUID_USERB=?2",nativeQuery = true)
    String findTargetRelationUUID(String operationUserUUID,String targetUserUUID);
    //拉黑某人
    @Query(value = "update TB_USERRELATION set RELATION_STATUS=3 where UUID_USERA=?1 and UUID_USERB=?2",nativeQuery = true)
    String sendIntoBlackList(String operationUserUUID,String targetUserUUID);
    //取消拉黑某人
    @Query(value = "update TB_USERRELATION set RELATION_STATUS=1 where UUID_USERA=?1 and UUID_USERB=?2",nativeQuery = true)
    String outFromBlackList(String operationUserUUID,String targetUserUUID);
    //屏蔽某人
    @Query(value = "update TB_USERRELATION set RELATION_STATUS=2 where UUID_USERA=?1 and UUID_USERB=?2",nativeQuery = true)
    String unFriendSomeone(String operationUserUUID,String targetUserUUID);
    //取消屏蔽某人
    @Query(value = "update TB_USERRELATION set RELATION_STATUS=1 where UUID_USERA=?1 and UUID_USERB=?2",nativeQuery = true)
    String unUnFriendSomeone(String operationUserUUID,String targetUserUUID);
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
            ")",nativeQuery = true)
    List<String> getCommonFriends(String userA,String userB);
}
