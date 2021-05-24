package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.transform.api.service.IBaseInfoService;
import com.transform.base.response.ResponseData;
import com.transform.base.response.ResponseUtil;
import com.transform.web.util.AsyncUtil;
import com.transform.web.util.MyIOUtil;
import com.transform.web.util.PicUtil;
import com.transform.web.util.WebTools;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
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
    @Autowired
    MyIOUtil myIOUtil;

    @RequestMapping(value = "/upload/file", method = RequestMethod.POST)
    public Object upload(@RequestParam(value = "files") MultipartFile[] list,
                         @RequestParam(value = "inputText") String text) {
        System.out.println("传入 " + list.length + " 个文件");
        System.out.println("传入文本值 " + text);
        //longList.add(text);
        List list1 = Arrays.stream(list).map(MultipartFile::getSize).collect(Collectors.toList());
        List list2 = Arrays.stream(list).map(MultipartFile::getOriginalFilename).collect(Collectors.toList());
        List list3 = Arrays.stream(list).map(MultipartFile::getContentType).collect(Collectors.toList());
        List list4 = Arrays.stream(list).map(MultipartFile::getName).collect(Collectors.toList());

        System.out.println("文件尺寸 " + JSON.toJSONString(list1) + "\n" +
                "文件名 " + JSON.toJSONString(list2) + "\n" +
                "文件类型 " + JSON.toJSONString(list3) + "\n" +
                "文件名 " + JSON.toJSONString(list4)
        );
        return "success";
    }

    @RequestMapping(value = "getuuid",method = RequestMethod.GET)
    public Object info() {
        System.out.println(System.getProperty("user.dir"));
        return System.getProperty("user.dir");
        //return baseInfoService.getUserInfo("齐天大圣");
    }

    /**
     * @return
     */
    @RequestMapping(value = "asyncTest",method = RequestMethod.GET)
    public void asyncTest() throws InterruptedException {
        System.out.println("测试开始");

        asyncUtil.pushMoment(null, null);

        System.out.println("测试结束");
    }

    @ApiOperation(value = "更新/上传动态")
    @PostMapping(value = "/fileUploadTest")
    public ResponseData fileUpload(@ApiParam("多选") @RequestParam(value = "file") MultipartFile[] list,
                                   @ApiParam("参数(动态内容，是否编辑，设备名必传)") @RequestParam(value = "text") String text,
                                   HttpServletRequest request) throws InterruptedException {
        System.out.println(list.length);
        System.out.println(text);
        return ResponseUtil.success("上传成功！");
    }

    @Autowired
    PicUtil picUtil;

    @ApiOperation(value = "更新/上传动态")
    @PostMapping(value = "/pic/speed")
    public ResponseData picSpeed() throws IOException {
        picUtil.getPicUrl();
        return ResponseUtil.success("上传成功！");
    }

}

@Data
class Entiy implements Serializable {
    public Entiy() {
    }

    private String name;
    private String age;
    private String id;

    public Entiy(String name, String age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entiy{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}