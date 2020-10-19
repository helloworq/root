package com.transform.service;


import com.transform.api.model.entiy.UserAccount;
import com.transform.api.model.entiy.UserInfo;
import com.transform.api.model.entiy.UserRelation;
import com.transform.service.dao.UserAccountRepositry;
import com.transform.service.dao.UserInfoRepositry;
import com.transform.service.dao.UserRelationRepositry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ServiceApplicationTests {
    @Autowired
    UserAccountRepositry userAccountRepositry;
    @Autowired
    UserRelationRepositry userRelationRepositry;
    @Autowired
    UserInfoRepositry userInfoRepositry;
    @Test
    void contextLoads() {
        System.out.println("开始");
        UserInfo userInfo=new UserInfo();
        List list=new ArrayList(){};
        String[] a=new String[1];
        System.out.println(ObjectUtils.isEmpty(userInfo));
        System.out.println(ObjectUtils.isEmpty(list));
        System.out.println(ObjectUtils.isEmpty(a));

        System.out.println(a.length);
        System.out.println("结束");
    }

    @Test
    void addUser(){
        for (int i = 0; i < 10; i++) {
            UserInfo userInfo=new UserInfo();
            userInfo.setUserWechatNum(String.valueOf(i));
            userInfo.setUserSex(String.valueOf(i));
            userInfo.setUserQQNum(String.valueOf(i));
            userInfo.setUserPhoneNum(String.valueOf(i));
            userInfo.setUserJoinTime(new Date());
            userInfo.setUserAge(String.valueOf(i));
            userInfo.setUserBlogLink(String.valueOf(i));
            userInfo.setUserEmail(String.valueOf(i));
            userInfo.setUserHeadUrl(String.valueOf(i));
            userInfo.setUserName(String.valueOf(i));
            userInfo.setRedisMsgSize("0");
            userInfoRepositry.save(userInfo);
        }
    }

    @Test
    void addUserRelation(){
        for (int i = 0; i < 10; i++){
            UserRelation userRelation=new UserRelation();
            userRelation.setOperTime(new Date());
            userRelation.setRelationStatus(1);

            userRelation.setUuidUserA(String.valueOf(i));
            userRelation.setUuidUserB(String.valueOf(i));
            userRelationRepositry.save(userRelation);
        }
    }

}