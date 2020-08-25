package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.service.IBaseInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    public HashMap<String,String> getjqdata(
            @RequestParam("str") String s,
            @RequestParam("obj") Object o){
        System.out.println(s);
        System.out.println(o);
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("msg","got");
        return hashMap;
    }
}
