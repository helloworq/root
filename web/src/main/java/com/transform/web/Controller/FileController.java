package com.transform.web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.model.dto.UserMomentInfoDTO;
import com.transform.api.model.entiy.UserMomentInfo;
import com.transform.api.model.entiy.mongo.ResourceInfo;
import com.transform.api.service.IMomentService;
import com.transform.api.service.IStrogeService;
import com.transform.base.util.ListUtil;
import com.transform.web.util.MyIOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传
 */
@Api("上传文件")
@RestController
public class FileController {
    @Reference
    IStrogeService strogeService;
    @Reference
    IMomentService momentService;
    @Autowired
    MyIOUtil myIOUtil;
    /**
     * 上传文件以及文件信息到mongo和oracle，dubbo下文件无法序列化导致难以传输，
     * 故采取先上传到临时目录的方法
     * @param list
     * @param userMomentInfoDTO
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/fileUpload")
    public String fileUpload(@ApiParam("多选") @RequestParam(value = "file") MultipartFile[] list,
                             @ApiParam("参数") UserMomentInfoDTO userMomentInfoDTO) throws IOException {
        if (list.length==0||null==list)
            return null;
        //判断是更新请求还是上传请求,是更新请求则删除已存信息
        if (null!=userMomentInfoDTO.getId()) {
            //删除已存文件
            UserMomentInfoDTO momentInfo=momentService.getUserMomentInfo(userMomentInfoDTO.getId());
            List<String> picIds = ListUtil.stringToList(momentInfo.getPicIds());
            for (String s : picIds) {
                strogeService.deleteMongoFile(s);
            }
        }

        List<String> imageIds=new ArrayList<>();
        for (MultipartFile file: list) {
            imageIds.add(myIOUtil.saveToTempPath(file));//存入到Mongo
        }
        userMomentInfoDTO.setPicIds(ListUtil.listToString(imageIds));
        momentService.uploadMoment(userMomentInfoDTO);//上传动态到oracle
        return "上传成功！";
    }

    /**
     * 获取全部动态
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/getAllMonment")
    public List<UserMomentInfo> getAllMonment() {
        return momentService.getAllUserMomentInfo();
    }

    /**
     * 根据id删除动态
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteUserMoment")
    public String deleteUserMoment(@RequestParam("id")String id) {
        return momentService.deleteUserMoment(id);
    }

    /**
     * 根据id获取某一条动态信息,此时picIds需要换成链接
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/getDTO")
    public UserMomentInfoDTO getDTO(@RequestParam("id")String id) throws IOException {
        UserMomentInfoDTO userMomentInfoDTO=momentService.getUserMomentInfo(id);
        //将id转换成链接
        String picIds=userMomentInfoDTO.getPicIds();
        List<String> picList=ListUtil.stringToList(picIds);
        List<String> newPicList=new ArrayList<>();
        for (String s:picList) {
            byte[] fileBytes=strogeService.getMongoFileInputStream(s);//dubbo只能传输字节
            InputStream fileInputStream = new ByteArrayInputStream(fileBytes);
            ResourceInfo resourceInfo =(ResourceInfo) strogeService.getObject(s , ResourceInfo.class);
            String filePath=myIOUtil.creatRandomNameFile(
                    System.getProperty("user.dir") + "/data/downloadTmp/", resourceInfo.getFileSuffix());
            OutputStream fileOutputStream=new FileOutputStream(new File(filePath));
            myIOUtil.inputStreamWriteToOutputStream(fileInputStream,fileOutputStream);
            //写入完成之后将数据拼成可访问的链接
            String url="localhost:6729/"+"upload"+filePath.substring(filePath.lastIndexOf("/"));
            newPicList.add(url);
        }
        userMomentInfoDTO.setPicIds(ListUtil.listToString(newPicList));
        return userMomentInfoDTO;
    }
}