package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.service.ILoginService;
import com.transform.base.util.GenerateCaptchaUtil;
import com.transform.web.util.WebTools;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Api("登录控制器")
@RestController
@RequestMapping("v1/rest/")
public class LoginController {
    @Reference
    ILoginService loginService;

    @Autowired
    WebTools tools;

    /**
     * 验证码验证完成后开始验证输入的账号与密码是否匹配
     * 假如账号密码验证码全部验证成功则跳转进主页面，并且加入cookie值用来根据用户名拉取数据
     * 三项验证成功后返回给浏览器对应的cookie值
     */
    @GetMapping("loginCheck")
    public Map<String, Object> loginCheck(@RequestParam(value = "inputAccount") String inputAccount,
                                          @RequestParam(value = "inputPassword") String inputPassword,
                                          @RequestParam(value = "inputCaptcha") String inputCaptcha,
                                          HttpServletResponse httpResponse) {
        Map<String, Object> map = new HashMap<>();
        if (!inputCaptcha.equals(GenerateCaptchaUtil.getGeneratedString())) {
            map.put("msg", "验证码错误");
            return map;
        }
        if (checkPassword(inputAccount, inputPassword)) {
            map.put("msg", "success");
            httpResponse.addCookie(new Cookie("userName", inputAccount));
            return map;
        } else {
            System.out.println("验证失败");
            map.put("msg", "fail");
            return map;
        }
    }

    /**
     * 添加账户
     *
     * @param userAccount
     * @param userPassword
     * @return
     */
    @PostMapping("Account")
    public String addAccount(@RequestParam(value = "userAccount") String userAccount,
                             @RequestParam(value = "userPassword") String userPassword) {
        if (loginService.isUserAccountUnique(userAccount)) {
            loginService.addAccount(userAccount, userPassword);
            return "success";
        } else {
            return "账户名已存在，请修改账户名";
        }
    }

    /**
     * 删除账户
     *
     * @param userAccount
     * @param userPassword
     * @return
     */
    @DeleteMapping("Account")
    public String deleteAccount(@RequestParam(value = "userAccount") String userAccount,
                                @RequestParam(value = "userPassword") String userPassword) {
        if (loginService.checkPassword(userAccount, userPassword)) {
            loginService.deleteAccount(userAccount, userPassword);
            return "删除账户成功！";
        } else {
            return "删除失败！";
        }
    }

    /**
     * 校验账户
     *
     * @param userAccount
     * @param userPassword
     * @return
     */
    @GetMapping("checkPassword")
    public boolean checkPassword(@RequestParam(value = "userAccount") String userAccount,
                                 @RequestParam(value = "userPassword") String userPassword) {
        return loginService.checkPassword(userAccount, userPassword) ;
    }

}
