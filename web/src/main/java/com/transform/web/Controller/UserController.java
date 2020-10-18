package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.model.dto.UserInfoDTO;
import com.transform.api.model.entiy.UserInfo;
import com.transform.api.service.IBaseInfoService;
import com.transform.base.response.ResponseData;
import com.transform.base.response.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("用户信息控制器")
@RestController
@RequestMapping("/v1/rest")
public class UserController {
    @Reference
    IBaseInfoService baseInfoService;

    /**
     * 更新或上传用户信息
     * @param userInfoDTO
     * @return
     */
    @PostMapping("/uploadUserInfo")
    public ResponseData uploadUserInfo(@ApiParam @Validated UserInfoDTO userInfoDTO){
        UserInfo userInfo=new UserInfo();
        BeanUtils.copyProperties(userInfoDTO,userInfo);
        return ResponseUtil.success(baseInfoService.uploadUserInfo(userInfo));
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @PostMapping("/deleteUserInfo")
    public ResponseData deleteUserInfo(@ApiParam @RequestParam("id") String id){
        return ResponseUtil.success(baseInfoService.deleteUserInfo(id));
    }

    /**
     * 获取全部用户信息
     * @return
     */
    @PostMapping("/getAllUserInfo")
    public ResponseData getAllUserInfo(){
        return ResponseUtil.success(baseInfoService.getAllUserInfo());
    }

    /**
     * 获取指定用户信息
     * @param id
     * @return
     */
    @PostMapping("/getUserInfo")
    public ResponseData getAllUserInfo(@ApiParam @RequestParam("id") String id){
        return ResponseUtil.success(baseInfoService.getUserInfo(id));
    }
}
