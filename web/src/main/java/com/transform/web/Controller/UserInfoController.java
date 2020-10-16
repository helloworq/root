package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.model.dto.UserInfoDTO;
import com.transform.api.model.entiy.UserInfo;
import com.transform.api.service.IBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api("用户信息控制器")
@RestController
@RequestMapping("/v1/rest")
public class UserInfoController {
    @Reference
    IBaseInfoService baseInfoService;

    @PostMapping("/uploadUserInfo")
    public String uploadUserInfo(@ApiParam @Validated UserInfoDTO userInfoDTO){
        UserInfo userInfo=new UserInfo();
        BeanUtils.copyProperties(userInfoDTO,userInfo);
        //baseInfoService.uploadUserInfo(userInfo);
        return "success";
    }
}
