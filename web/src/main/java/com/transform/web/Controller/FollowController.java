package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.model.dto.UserBaseInfoDTO;
import com.transform.api.service.IBaseInfoService;
import com.transform.api.service.IFollowService;
import com.transform.base.response.ResponseData;
import com.transform.base.response.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 此类提供关于用户关注的功能
 */
@Api(description = "用户关注控制器")
@RestController
@RequestMapping("v1/rest/")
public class FollowController {
    @Reference
    IBaseInfoService baseInfoService;
    @Reference
    IFollowService followService;

    @ApiOperation(value = "获取基础信息")
    @GetMapping("getbaseinfo")
    public ResponseData getbaseinfo(@RequestParam("userId") String userId){
        return ResponseUtil.success(baseInfoService.getUserBaseInfo(userId));
    }

    @ApiOperation(value = "关注")
    @GetMapping("follow")
    public ResponseData follow(@RequestParam("operationUserUUID") String operationUserUUID,
                               @RequestParam("targetUserUUID") String targetUserUUID){
        return ResponseUtil.success(followService.followSomeone(operationUserUUID, targetUserUUID));
    }

    @ApiOperation(value = "取消关注")
    @GetMapping("unfollow")
    public ResponseData unfollow(@RequestParam("operationUserUUID") String operationUserUUID,
                                 @RequestParam("targetUserUUID") String targetUserUUID){
        return ResponseUtil.success(followService.unFollowSomeone(operationUserUUID, targetUserUUID));
    }

    @ApiOperation(value = "屏蔽")
    @GetMapping("unfriend")
    public ResponseData unfriend(@RequestParam("operationUserUUID") String operationUserUUID,
                                 @RequestParam("targetUserUUID") String targetUserUUID){
        return ResponseUtil.success(followService.unFriendSomeone(operationUserUUID, targetUserUUID));
    }

    @ApiOperation(value = "取消屏蔽")
    @GetMapping("unUnfriend")
    public ResponseData unUnfriend(@RequestParam("operationUserUUID") String operationUserUUID,
                                   @RequestParam("targetUserUUID") String targetUserUUID){
        return ResponseUtil.success(followService.unUnFriendSomeone(operationUserUUID, targetUserUUID));
    }

    @ApiOperation(value = "拉黑")
    @GetMapping("sendIntoBlackList")
    public ResponseData sendIntoBlackList(@RequestParam("operationUserUUID") String operationUserUUID,
                                          @RequestParam("targetUserUUID") String targetUserUUID){
        return ResponseUtil.success(followService.sendIntoBlackList(operationUserUUID, targetUserUUID));
    }

    @ApiOperation(value = "取消拉黑")
    @GetMapping("outFromBlackList")
    public ResponseData outFromBlackList(@RequestParam("operationUserUUID") String operationUserUUID,
                                                @RequestParam("targetUserUUID") String targetUserUUID){
        return ResponseUtil.success(followService.outFromBlackList(operationUserUUID, targetUserUUID));
    }

    @ApiOperation(value = "取消拉黑")
    @GetMapping("getFriendsList")
    public ResponseData getFriendsList(@RequestParam("operationUserUUID") String operationUserUUID){
        return ResponseUtil.success(followService.getFriendsList(operationUserUUID));
    }

    @ApiOperation(value = "取消拉黑")
    @GetMapping("getFans")
    public ResponseData getFans(@RequestParam("operationUserUUID") String operationUserUUID){
        return ResponseUtil.success(followService.getFans(operationUserUUID));
    }
}
