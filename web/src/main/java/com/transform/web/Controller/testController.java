package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.service.IBaseInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class testController {
    @Reference
    IBaseInfoService baseInfoService;

    @RequestMapping("getuuid")
    public Map<String,Object> info(){
        HashMap<String,Object> hashMap=new HashMap<>();
        String uuid=baseInfoService.getUserUUID("水水水");
        hashMap.put("info", uuid);
        return hashMap;
    }

    @RequestMapping("getjqdata")
    public HashMap<String,Object> getjqdata(){
        someDTO someDTO1=new someDTO();

        someDTO1.setCreateTime(new Date());

        List<String> list=new ArrayList<>();
        list.add("horse.ogg");

        someDTO1.setAudioIds(list);
        someDTO1.setBlockrangeId(1L);
        someDTO1.setGeometry("a");

        List<String> list1=new ArrayList<>();
        list1.add("dasd.png");

        someDTO1.setImageIds(list1);
        someDTO1.setImagesUrl(list1);
        someDTO1.setOpinion("s");
        someDTO1.setQuestion("das");
        someDTO1.setUserName("Dd");
        someDTO1.setPatrolName("sds");
        someDTO1.setId(89L);
        someDTO1.setBlockrangeId(99L);


        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("msg",someDTO1);
        return hashMap;
    }
}
