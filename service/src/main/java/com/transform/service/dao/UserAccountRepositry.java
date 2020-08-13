package com.transform.service.dao;

import com.transform.api.model.entiy.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = UserAccount.class, idClass = String.class)
public interface UserAccountRepositry extends JpaRepository<UserAccount,String> {

    @Query(value = "select id from tb_user u where u.useraccount=?1",nativeQuery = true)
    String findUUIDbyUserAccount(String useraccount);

}
