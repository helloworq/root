package com.transform.service;

import com.transform.api.model.entiy.UserAccount;
import com.transform.service.dao.UserAccountRepositry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceApplicationTests {
    @Autowired
    UserAccountRepositry userAccountRepositry;
    @Test
    void contextLoads() {
        System.out.println("开始");
        System.out.println(userAccountRepositry.findUUIDbyUserAccount("秦始皇"));
        System.out.println("结束");
    }

}
