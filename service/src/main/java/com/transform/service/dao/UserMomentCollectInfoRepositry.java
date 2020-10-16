package com.transform.service.dao;

import com.transform.api.model.entiy.UserMomentCollectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = UserMomentCollectInfo.class, idClass = String.class)
public interface UserMomentCollectInfoRepositry extends JpaRepository<UserMomentCollectInfo,String> {
    UserMomentCollectInfo findByMomentIdAndAndWhoCollect(String momentId,String whoCollect);
}