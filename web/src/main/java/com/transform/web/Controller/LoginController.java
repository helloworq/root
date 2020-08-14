package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.service.ILoginService;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@RestController
@RequestMapping("v1/rest/")
public class LoginController {
    @Reference
    ILoginService loginService;

    /**
     * 添加账户
     * @param userAccount
     * @param userPassword
     * @return
     */
    @PostMapping("Account")
    public String addAccount(@RequestParam(value = "userAccount") String userAccount,
                             @RequestParam(value = "userPassword") String userPassword){
        loginService.addAccount(userAccount,userPassword);
        return "success";
    }

    /**
     * 删除账户
     * @param userAccount
     * @param userPassword
     * @return
     */
    @DeleteMapping("Account")
    public String deleteAccount(@RequestParam(value = "userAccount") String userAccount,
                                @RequestParam(value = "userPassword") String userPassword){
        if (loginService.checkPassword(userAccount, userPassword)) {
            loginService.deleteAccount(userAccount, userPassword);
            return "删除账户成功！";
        }
        else
            return "删除失败！";
    }

    /**
     * 校验账户
     * @param userAccount
     * @param userPassword
     * @return
     */
    @GetMapping("checkPassword")
    public String checkPassword(@RequestParam(value = "userAccount") String userAccount,
                                @RequestParam(value = "userPassword") String userPassword){
        return loginService.checkPassword(userAccount,userPassword) ==true?"true":"false";
    }

}
