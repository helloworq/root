package com.transform.service;

import com.alibaba.dubbo.common.json.JSON;
import com.transform.api.model.entiy.UserAccount;
import com.transform.api.model.entiy.UserMomentCommentInfo;
import com.transform.service.dao.UserAccountRepositry;
import lombok.Data;
import net.minidev.json.JSONUtil;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Column;
import java.util.Date;

@SpringBootTest
class ServiceApplicationTests {
    @Autowired
    UserAccountRepositry userAccountRepositry;
    @Test
    void contextLoads() {
        System.out.println("开始");
        UserMomentCommentInfo userMomentCommentInfo=new UserMomentCommentInfo();
        userMomentCommentInfo.setCommentContent("普天之下莫非王土，率土之滨莫非王臣");
        userMomentCommentInfo.setId("1");
        userMomentCommentInfo.setCommentTime(new Date());
        userMomentCommentInfo.setWhoComment("秦始皇");
        userMomentCommentInfo.setMomentId("2");

        dto dto1=new dto();
        BeanUtils.copyProperties(userMomentCommentInfo,dto1);
        System.out.println(dto1.getCommentContent()+"\n"+
                           dto1.getWhoComment()+"\n"+
                           dto1.getCommentTime());
        System.out.println("结束");
    }

}
@Data
class dto{
    private String commentContent;

    private String whoComment;

    private Date commentTime;
}