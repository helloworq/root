package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.transform.api.service.IBaseInfoService;
import com.transform.web.util.WebTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

//@CrossOrigin(origins = "*")
@RestController
public class testController {
    @Reference
    IBaseInfoService baseInfoService;
    @Autowired
    WebTools tools;
    @RequestMapping(value = "/upload/file",method = RequestMethod.POST)
    public Object upload(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("msg","success");

        JSONObject jsonObjectPrev=new JSONObject();
        jsonObjectPrev.put("code",200);
        jsonObjectPrev.put("msg","success");

        jsonObject.put("data",tools.getUrl());
        return jsonObject;
    }

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
