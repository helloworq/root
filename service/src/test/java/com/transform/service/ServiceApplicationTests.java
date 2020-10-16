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

import java.util.Date;

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
        /*UserInfo userInfo=new UserInfo();
        userInfo.setUserAge("2");
        userInfo.setUserBlogLink("www.baidu,com");
        userInfo.setUserEmail("123@qq.com");
        userInfo.setUserHeadUrl("www.google.com");
        userInfo.setUserJoinTime(new Date());
        userInfo.setUserName("水水水");
        userInfo.setUserPhoneNum("15751266432");
        userInfo.setUserQQNum("312323121");
        userInfo.setUserSex("n");
        userInfo.setUserWechatNum("阿萨大大");
        userInfoRepositry.save(userInfo);*/
        UserAccount userAccount=new UserAccount();
        userAccount.setUserPassword("ss");
        userAccount.setUserAccount("ss");
        userAccountRepositry.save(userAccount);
        System.out.println("结束");
    }

}