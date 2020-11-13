package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.model.dto.UserInfoDTO;
import com.transform.api.model.entiy.UserInfo;
import com.transform.api.service.IBaseInfoService;
import com.transform.base.response.ResponseData;
import com.transform.base.response.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "用户信息控制器")
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
    @ApiOperation(value = "更新或上传用户信息")
    @PostMapping("/uploadUserInfo")
    public ResponseData uploadUserInfo(@ApiParam @Validated UserInfoDTO userInfoDTO){
        UserInfo userInfo=new UserInfo();
        BeanUtils.copyProperties(userInfoDTO,userInfo);
        //return ResponseUtil.success(baseInfoService.uploadUserInfo(userInfo));
        return null;
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户信息")
    @PostMapping("/deleteUserInfo")
    public ResponseData deleteUserInfo(@ApiParam @RequestParam("id") String id){
        return ResponseUtil.success(baseInfoService.deleteUserInfo(id));
    }

    /**
     * 获取全部用户信息
     * @return
     */
    @ApiOperation(value = "获取全部用户信息")
    @PostMapping("/getAllUserInfo")
    public ResponseData getAllUserInfo(){
        return ResponseUtil.success(baseInfoService.getAllUserInfo());
    }

    /**
     * 获取指定用户信息-byId
     * @param name
     * @return
     */
    @ApiOperation(value = "获取指定用户信息")
    @PostMapping("/getUserInfo")
    public ResponseData getAllUserInfo(@ApiParam @RequestParam("name") String name){
        return ResponseUtil.success(baseInfoService.getUserInfo(name));
    }

}
