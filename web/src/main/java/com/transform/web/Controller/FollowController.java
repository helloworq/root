package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.model.dto.UserBaseInfoDTO;
import com.transform.api.service.IBaseInfoService;
import com.transform.api.service.IFollowService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 此类提供关于用户关注的功能
 */
@RestController
@RequestMapping("v1/rest/")
public class FollowController {
    @Reference
    IBaseInfoService baseInfoService;
    @Reference
    IFollowService followService;

    @GetMapping("getbaseinfo")
    public Map<String,Object> getbaseinfo(){
        HashMap<String,Object> hashMap=new HashMap<>();
        UserBaseInfoDTO userBaseInfoDTO=baseInfoService.getUserBaseInfo("4028825573ebbe240173ebbe84840000");
        hashMap.put("info", userBaseInfoDTO);
        return hashMap;
    }

    @GetMapping("follow")
    public Map<String,Object> follow(@RequestParam("operationUserUUID") String operationUserUUID,
                                     @RequestParam("targetUserUUID") String targetUserUUID){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("msg",followService.followSomeone(operationUserUUID, targetUserUUID));
        return hashMap;
    }

    @GetMapping("unfollow")
    public Map<String,Object> unfollow(@RequestParam("operationUserUUID") String operationUserUUID,
                                       @RequestParam("targetUserUUID") String targetUserUUID){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("msg",followService.unFollowSomeone(operationUserUUID, targetUserUUID));
        return hashMap;
    }

    @GetMapping("unfriend")
    public Map<String,Object> unfriend(@RequestParam("operationUserUUID") String operationUserUUID,
                                       @RequestParam("targetUserUUID") String targetUserUUID){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("msg",followService.unFriendSomeone(operationUserUUID, targetUserUUID));
        return hashMap;
    }

    @GetMapping("unUnfriend")
    public Map<String,Object> unUnfriend(@RequestParam("operationUserUUID") String operationUserUUID,
                                       @RequestParam("targetUserUUID") String targetUserUUID){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("msg",followService.unUnFriendSomeone(operationUserUUID, targetUserUUID));
        return hashMap;
    }

    @GetMapping("sendIntoBlackList")
    public Map<String,Object> sendIntoBlackList(@RequestParam("operationUserUUID") String operationUserUUID,
                                         @RequestParam("targetUserUUID") String targetUserUUID){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("msg",followService.sendIntoBlackList(operationUserUUID, targetUserUUID));
        return hashMap;
    }

    @GetMapping("outFromBlackList")
    public Map<String,Object> outFromBlackList(@RequestParam("operationUserUUID") String operationUserUUID,
                                                @RequestParam("targetUserUUID") String targetUserUUID){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("msg",followService.outFromBlackList(operationUserUUID, targetUserUUID));
        return hashMap;
    }
}
