package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.service.ILoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

@RestController
public class LoginController {
    @Reference
    ILoginService loginService;

    @PostMapping("checkPassword")
    public String check(){
        loginService.addAccount("1273370082","abcdefg");
        return "success";
    }

    @GetMapping("check")
    public String checkppp(){
        if(loginService.checkPassword("1273370082","abcdefg"))
            return "true";
        else
            return "false";
    }
}
