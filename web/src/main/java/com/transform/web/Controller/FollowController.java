package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.service.IBaseInfoService;
import com.transform.api.service.IFollowService;
import com.transform.base.constant.Global_Constant;
import com.transform.base.response.ResponseData;
import com.transform.base.response.ResponseUtil;
import com.transform.base.util.ListUtil;
import com.transform.web.util.MyIOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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

    @Autowired
    MyIOUtil myIOUtil;

    @ApiOperation(value = "获取基础信息")
    @GetMapping("getbaseinfo")
    public ResponseData getbaseinfo(@RequestParam("userId") String userId) {
        return ResponseUtil.success(baseInfoService.getUserBaseInfo(userId));
    }

    @ApiOperation(value = "关注")
    @GetMapping("relation/follow")
    public ResponseData follow(@RequestParam("operationUserUUID") String operationUserUUID,
                               @RequestParam("targetUserUUID") String targetUserUUID) {
        return ResponseUtil.success(followService.followSomeone(operationUserUUID, targetUserUUID));
    }

    @ApiOperation(value = "取消关注")
    @GetMapping("relation/unfollow")
    public ResponseData unfollow(@RequestParam("operationUserUUID") String operationUserUUID,
                                 @RequestParam("targetUserUUID") String targetUserUUID) {
        return ResponseUtil.success(followService.unFollowSomeone(operationUserUUID, targetUserUUID));
    }

    @ApiOperation(value = "屏蔽")
    @GetMapping("relation/unfriend")
    public ResponseData unfriend(@RequestParam("operationUserUUID") String operationUserUUID,
                                 @RequestParam("targetUserUUID") String targetUserUUID) {
        return ResponseUtil.success(followService.unFriendSomeone(operationUserUUID, targetUserUUID));
    }

    @ApiOperation(value = "取消屏蔽")
    @GetMapping("relation/unUnfriend")
    public ResponseData unUnfriend(@RequestParam("operationUserUUID") String operationUserUUID,
                                   @RequestParam("targetUserUUID") String targetUserUUID) {
        return ResponseUtil.success(followService.unUnFriendSomeone(operationUserUUID, targetUserUUID));
    }

    @ApiOperation(value = "拉黑")
    @GetMapping("relation/sendIntoBlackList")
    public ResponseData sendIntoBlackList(@RequestParam("operationUserUUID") String operationUserUUID,
                                          @RequestParam("targetUserUUID") String targetUserUUID) {
        return ResponseUtil.success(followService.sendIntoBlackList(operationUserUUID, targetUserUUID));
    }

    @ApiOperation(value = "取消拉黑")
    @GetMapping("relation/outFromBlackList")
    public ResponseData outFromBlackList(@RequestParam("operationUserUUID") String operationUserUUID,
                                         @RequestParam("targetUserUUID") String targetUserUUID) {
        return ResponseUtil.success(followService.outFromBlackList(operationUserUUID, targetUserUUID));
    }

    @ApiOperation(value = "获取关注好友列表")
    @GetMapping("relation/get/{relationList}")
    public ResponseData getFriendsList(@RequestParam("operationUserUUID") String operationUserUUID,
                                       @PathVariable String relationList) {

        Integer relationStatus = Global_Constant.SOCIAL_RELATION.get(relationList);

        if (relationStatus == 2) {
            return ResponseUtil.fail("empty");
        }

        return ResponseUtil.success(
                followService.relationList(operationUserUUID, relationStatus)
                        .stream()
                        .peek(ele -> {
                            try {
                                ele.setUserHeadUrl(ListUtil.listToString(myIOUtil.picIdsToLinks(Arrays.asList(ele.getUserHeadUrl()))));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        })
        );
    }

    @ApiOperation(value = "获取粉丝列表")
    @GetMapping("relation/get/FansList")
    public ResponseData getFans(@RequestParam("operationUserUUID") String operationUserUUID) {
        return ResponseUtil.success(
                followService.getFans(operationUserUUID)
                        .stream()
                        .peek(ele -> {
                            try {
                                ele.setUserHeadUrl(ListUtil.listToString(myIOUtil.picIdsToLinks(Arrays.asList(ele.getUserHeadUrl()))));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }));
    }

    /**
     * 获取全部用户信息
     *
     * @return
     */
    @ApiOperation(value = "获取全部用户信息")
    @GetMapping("relation/get/friendSquareList")
    public ResponseData getAllUserInfo(@RequestParam("operationUserUUID") String operationUserUUID) {
        return ResponseUtil.success(
                baseInfoService.getAllUserInfo()
                        .stream()
                        .peek(ele -> {
                            try {
                                ele.setUserHeadUrl(ListUtil.listToString(myIOUtil.picIdsToLinks(Arrays.asList(ele.getUserHeadUrl()))));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }));
    }
}
