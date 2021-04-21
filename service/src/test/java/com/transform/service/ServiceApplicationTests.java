package com.transform.service;


import com.alibaba.fastjson.JSON;
import com.transform.api.model.dto.UserBaseInfoDTO;
import com.transform.api.model.entiy.UserInfo;
import com.transform.api.model.entiy.UserRelation;
import com.transform.api.service.IBaseInfoService;
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
    @Autowired
    IBaseInfoService baseInfoService;

    @Test
    void getCommonFriends() {
        System.out.println("开始");
        List<String> commonFriendA = userRelationRepositry.getCommonFriends("齐天大圣", "二郎神");
        List<String> commonFriendB = userRelationRepositry.getCommonFriends("哮天犬", "如来");
        List<String> commonFriendC = userRelationRepositry.getCommonFriends("二郎神", "齐天大圣");
        System.out.println(JSON.toJSONString(commonFriendA));
        System.out.println(JSON.toJSONString(commonFriendB));
        System.out.println(JSON.toJSONString(commonFriendC));
        System.out.println("结束");
    }

    @Test
    void test() {
        System.out.println("开始");
        System.out.println(baseInfoService.getUserId("齐天大圣"));
        UserBaseInfoDTO userBaseInfoDTO = baseInfoService.getUserBaseInfo(baseInfoService.getUserId("齐天大圣"));
        System.out.println(userBaseInfoDTO);
        System.out.println("结束");
    }

    @Test
    void addUser() {
        String[] legendPeople = (
                "丁仪（正礼）,丁奉（承渊）,丁原（建阳）,丁谧（彦靖）,丁廙（敬礼）," +
                        "于禁（文则）,士孙瑞（君荣）,山涛（巨源）,卫瓘（伯玉）,马磾（翁叔）," +
                        "马良（季常）,马忠（德信）,马超（孟起）,马谡（幼常）,马腾（寿成）," +
                        "王允（子师）,王双（子全）,王平（子均）,王匡（公节）,王戎（睿冲）," +
                        "王观（伟台）,王甫（国山）,王连（文仪）,王沈（处道）,王肃（子雍）," +
                        "王修（叔治）,王浑（玄冲）,王路（文舒）,王颀（孔硕）,王祥（休徵）," +
                        "王朗（景兴）,王基（伯舆）,王谋（元泰）,王粲（仲宣）,王睿（土治）," +
                        "韦康（元将）,太史慈（子义）,毛玠（孝先）,公孙度（升济）,公孙瓒（伯圭）," +
                        "文钦（仲若）,文聘（仲业）,尹奉（次曾）,邓艾（土载）,邓芝（伯苗）," +
                        "邓止飏（玄茂）,孔伷（公绪）,孔昱（世元）,孔融（文举）,母丘甸（子邦）," +
                        "母丘俭（仲恭）,甘宁（兴霸）,左慈（元放）,卢植（子干）,申耽（义举）," +
                        "田丰（元皓）,田畴（子泰）,田豫（国让）,史涣（公刘）,乐进（文谦）"
                ).split(",");
        for (int i = 0; i < 60; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserWechatNum(String.valueOf(i));
            userInfo.setUserSex(String.valueOf(i));
            userInfo.setUserQQNum(String.valueOf(i));
            userInfo.setUserPhoneNum(String.valueOf(i));
            userInfo.setUserJoinTime(new Date());
            userInfo.setUserAge(String.valueOf(i));
            userInfo.setUserBlogLink("www.blog" + i + ".com");
            userInfo.setUserEmail("mail@163" + i);
            userInfo.setUserHeadUrl("5fae0cb0d372233ea2a0bbbb");
            userInfo.setUserName(legendPeople[i]);
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