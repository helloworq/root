package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.model.dto.UserBaseInfoDTO;
import com.transform.api.service.IBaseInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("v1/rest/")
public class FollowController {
    @Reference
    IBaseInfoService baseInfoService;

    @RequestMapping("getbaseinfo")
    public Map<String,Object> info(){
        HashMap<String,Object> hashMap=new HashMap<>();
        UserBaseInfoDTO userBaseInfoDTO=baseInfoService.getUserBaseInfo("4028825573ebbe240173ebbe84840000");
        hashMap.put("info", userBaseInfoDTO);
        return hashMap;
    }
}
