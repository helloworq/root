package com.transform.web.Controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传
 */
@Api("上传文件")
@RestController
public class FileController {

    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        System.out.println("文件类型："+file.getContentType());
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "C:\\Users\\12733\\Desktop\\Windows聚焦图片\\"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "\"filename\", filename";
    }


    @PostMapping(value = "/mulitFileUpload")
    public String mulitFileUpload(
            @ApiParam("多选") @RequestParam(value = "file") MultipartFile[] list,
            @ApiParam("参数") someDTO text) {
        System.out.println(list.length);
        System.out.println(JSON.toJSON(text));
        for (MultipartFile file:list) {
            if (file.isEmpty()) {
                return "";
            }
            System.out.println("文件类型："+file.getContentType());
            String fileName = file.getOriginalFilename();  // 文件名
            System.out.println(fileName);
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            String filePath = "C:\\Users\\12733\\Desktop\\Windows聚焦图片\\"; // 上传后的路径
            fileName = UUID.randomUUID() + suffixName; // 新文件名
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                System.out.println("错误！");
                e.printStackTrace();
            }
        }
        return "\"filename\", filename";
    }
}