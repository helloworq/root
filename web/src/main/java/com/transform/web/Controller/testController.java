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
        return hashMap;
    }
}
