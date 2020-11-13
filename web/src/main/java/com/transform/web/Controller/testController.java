package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.transform.api.model.dto.UserMomentInfoDTO;
import com.transform.api.service.IBaseInfoService;
import com.transform.web.util.AsyncUtil;
import com.transform.web.util.WebTools;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "*")
@RestController
public class testController {
    @Reference
    IBaseInfoService baseInfoService;
    @Autowired
    WebTools tools;
    @Autowired
    AsyncUtil asyncUtil;

    @RequestMapping(value = "/upload/file", method = RequestMethod.POST)
    public Object upload(@RequestParam(value = "files") MultipartFile[] list,
                         @RequestParam(value = "inputText") String text) {
        System.out.println("传入 " + list.length + " 个文件");
        System.out.println("传入文本值 " + text);
        //longList.add(text);
        List list1= Arrays.stream(list).map(MultipartFile::getSize).collect(Collectors.toList());
        List list2= Arrays.stream(list).map(MultipartFile::getOriginalFilename).collect(Collectors.toList());
        List list3= Arrays.stream(list).map(MultipartFile::getContentType).collect(Collectors.toList());
        List list4= Arrays.stream(list).map(MultipartFile::getName).collect(Collectors.toList());

        System.out.println( "文件尺寸 " + JSON.toJSONString(list1)+"\n"+
                            "文件名 " + JSON.toJSONString(list2)+"\n"+
                            "文件类型 " + JSON.toJSONString(list3)+"\n"+
                            "文件名 " + JSON.toJSONString(list4)
        );
        return "success";
    }

    @RequestMapping("getuuid")
    public Map<String, Object> info() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("", "");
        hashMap.get("");
        String a = "";
        return hashMap;
    }

    /**
     * @return
     */
    @RequestMapping("asyncTest")
    public void asyncTest() throws InterruptedException {
        System.out.println("测试开始");

        asyncUtil.pushMoment(null, null);

        System.out.println("测试结束");
    }
}
