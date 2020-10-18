package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.service.ILoginService;
import com.transform.base.response.ResponseData;
import com.transform.base.response.ResponseUtil;
import com.transform.base.util.GenerateCaptchaUtil;
import com.transform.web.util.WebTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "登录校验")
    public ResponseData loginCheck(@RequestParam(value = "inputAccount") String inputAccount,
                                   @RequestParam(value = "inputPassword") String inputPassword,
                                   @RequestParam(value = "inputCaptcha") String inputCaptcha,
                                   HttpServletResponse response) {
        if (!inputCaptcha.equals(GenerateCaptchaUtil.getGeneratedString())) {
            return ResponseUtil.fail("验证码错误");
        }
        if (checkPassword(inputAccount, inputPassword)) {
            response.addCookie(new Cookie("userName", inputAccount));
            return ResponseUtil.success("success");
        } else {
            return ResponseUtil.success("验证失败");
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
    @ApiOperation(value = "添加账户")
    public ResponseData addAccount(@RequestParam(value = "userAccount") String userAccount,
                                   @RequestParam(value = "userPassword") String userPassword) {
        if (loginService.isUserAccountUnique(userAccount)) {
            loginService.addAccount(userAccount, userPassword);
            return ResponseUtil.success("success");
        } else {
            return ResponseUtil.fail("账户名已存在，请修改账户名");
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
    @ApiOperation(value = "删除账户")
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
    @ApiOperation(value = "账户校验")
    @GetMapping("checkPassword")
    public boolean checkPassword(@RequestParam(value = "userAccount") String userAccount,
                                 @RequestParam(value = "userPassword") String userPassword) {
        return loginService.checkPassword(userAccount, userPassword) ;
    }

    /**
     * 测试阶段，简便添加cookie
     *
     * @return
     */
    @ApiOperation(value = "简便cookie添加")
    @GetMapping("addCookie")
    public void addCookie(@RequestParam(value = "cookieValue") String cookieValue,
                          HttpServletResponse response) {
        response.addCookie(new Cookie("userName", cookieValue));
    }

    /**
     * 测试接端，简便获取当前cookie
     *
     * @return
     */
    @ApiOperation(value = "简便cookie获取")
    @GetMapping("getCookie")
    public String getCookie(HttpServletRequest request) {
        return tools.getCookie(request.getCookies(),"userName");
    }
}
