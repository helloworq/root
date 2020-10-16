package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.model.dto.UserBaseInfoDTO;
import com.transform.api.service.IBaseInfoService;
import com.transform.api.service.IFollowService;
import com.transform.base.response.ResponseData;
import com.transform.base.response.ResponseUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 此类提供关于用户关注的功能
 */
@Api("用户关注控制器")
@RestController
@RequestMapping("v1/rest/")
public class FollowController {
    @Reference
    IBaseInfoService baseInfoService;
    @Reference
    IFollowService followService;

    @GetMapping("getbaseinfo")
    public ResponseData getbaseinfo(){
        return ResponseUtil.success(baseInfoService.getUserBaseInfo("4028825573ebbe240173ebbe84840000"));
    }

    @GetMapping("follow")
    public ResponseData follow(@RequestParam("operationUserUUID") String operationUserUUID,
                               @RequestParam("targetUserUUID") String targetUserUUID){
        return ResponseUtil.success(followService.followSomeone(operationUserUUID, targetUserUUID));
    }

    @GetMapping("unfollow")
    public ResponseData unfollow(@RequestParam("operationUserUUID") String operationUserUUID,
                                 @RequestParam("targetUserUUID") String targetUserUUID){
        return ResponseUtil.success(followService.unFollowSomeone(operationUserUUID, targetUserUUID));
    }

    @GetMapping("unfriend")
    public ResponseData unfriend(@RequestParam("operationUserUUID") String operationUserUUID,
                                 @RequestParam("targetUserUUID") String targetUserUUID){
        return ResponseUtil.success(followService.unFriendSomeone(operationUserUUID, targetUserUUID));
    }

    @GetMapping("unUnfriend")
    public ResponseData unUnfriend(@RequestParam("operationUserUUID") String operationUserUUID,
                                   @RequestParam("targetUserUUID") String targetUserUUID){
        return ResponseUtil.success(followService.unUnFriendSomeone(operationUserUUID, targetUserUUID));
    }

    @GetMapping("sendIntoBlackList")
    public ResponseData sendIntoBlackList(@RequestParam("operationUserUUID") String operationUserUUID,
                                          @RequestParam("targetUserUUID") String targetUserUUID){
        return ResponseUtil.success(followService.sendIntoBlackList(operationUserUUID, targetUserUUID));
    }

    @GetMapping("outFromBlackList")
    public ResponseData outFromBlackList(@RequestParam("operationUserUUID") String operationUserUUID,
                                                @RequestParam("targetUserUUID") String targetUserUUID){
        return ResponseUtil.success(followService.outFromBlackList(operationUserUUID, targetUserUUID));
    }
}
