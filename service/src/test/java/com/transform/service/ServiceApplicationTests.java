package com.transform.service;


import com.transform.api.model.entiy.UserInfo;
import com.transform.api.model.entiy.UserRelation;
import com.transform.service.dao.UserAccountRepositry;
import com.transform.service.dao.UserInfoRepositry;
import com.transform.service.dao.UserMomentInfoRepositry;
import com.transform.service.dao.UserRelationRepositry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

@SpringBootTest
class ServiceApplicationTests {
    @Autowired
    UserAccountRepositry userAccountRepositry;
    @Autowired
    UserRelationRepositry userRelationRepositry;
    @Autowired
    UserInfoRepositry userInfoRepositry;
    @Autowired
    UserMomentInfoRepositry userMomentInfoRepositry;

    @Test
    void test() {
        System.out.println("开始");
        userMomentInfoRepositry.incLikeCount("402881eb75c1e6450175c4a44a3c0000");
        System.out.println("结束");
    }

    @Test
    void addUser() {
        for (int i = 0; i < 10; i++) {
            UserInfo userInfo = new UserInfo();
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
    void addUserRelation() {
        //生成不重复随机关系
        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < 50; i++) {
            UserRelation userRelation = new UserRelation();
            userRelation.setOperTime(new Date());
            userRelation.setRelationStatus(1);

            int a = new Random().nextInt(10);
            int b = new Random().nextInt(10);
            String connectString = a + " <===> " + b;
            System.out.println(connectString);
            if (a != b && !hashSet.contains(connectString)) {
                hashSet.add(connectString);
                userRelation.setUuidUserA(userInfoRepositry.findId(String.valueOf(a)));
                userRelation.setUuidUserB(userInfoRepositry.findId(String.valueOf(b)));
                userRelationRepositry.save(userRelation);
            }
        }
    }

}