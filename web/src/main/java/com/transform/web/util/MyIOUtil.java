package com.transform.web.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.transform.api.model.entiy.mongo.ResourceInfo;
import com.transform.api.service.IStrogeService;
import com.transform.base.util.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MyIOUtil {
    @Reference
    IStrogeService strogeService;

    @Autowired
    WebTools tools;
    /**
     * 由于dubbo传输时只允许传字节数据，可通过引入Hessin依赖解决，暂时不采用
     * 为避免麻烦，上传的文件先保存在本地，然后再上传到Mongo，同时保存文件信息
     * 此时涉及到的数据操作有四个地方：
     * 1.oracle保存的动态信息，里面存有图片id与mongo里的数据关联
     * 2.mongo库存的信息，有文件描述信息存在Resource表里，
     * 3.还有Mongo库里的文件数据分为files表和chunks表
     * 4.本地存储的文件
     * 更新文件的策略采取删除全部已有文件和信息.
     * @param file
     * @return
     */
    public String saveToTempPath(MultipartFile file){
        String path=null;
        try {
            String fileName = UUID.randomUUID().toString().replace("-", "");
            String fileSuffix = FileUtil.getFileSuffix(file.getOriginalFilename());
            String fileSize = FileUtil.byteToMb(file.getSize());
            String fileTempPath = System.getProperty("user.dir") + "/data/tmp" + "/" + fileName + "." + fileSuffix;

            File dest = new File(fileTempPath);
            try {
                file.transferTo(dest);//存入本地缓存
            } catch (IOException e) {
                System.out.println("错误！");
                e.printStackTrace();
            }
            //上传缓存文件到mongo
            path = strogeService.uploadTempFile(fileTempPath);
            FileUtil.deleteFileUnderFolder(fileTempPath);//删除缓存目录下的所有文件
            //保存文件信息
            ResourceInfo resourceInfo = new ResourceInfo();
            resourceInfo.setId(path);
            resourceInfo.setFileName(fileName);
            resourceInfo.setFileSuffix(fileSuffix);
            resourceInfo.setFileSize(fileSize+"MB");
            resourceInfo.setFileTempPath(fileTempPath);

            strogeService.save(resourceInfo);
        } catch (Exception e) {
            System.out.println("传入文件存储失败");
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 将文件id转换为可访问的links
     *
     * @param picIds
     * @return
     * @throws IOException
     */
    public List<String> picIdsToLinks(List<String> picIds) throws IOException {
        List<String> newPicList = new ArrayList<>();
        for (String s : picIds) {
            long transform=System.currentTimeMillis();
            byte[] fileBytes = strogeService.getMongoFileBytes(s);//dubbo只能传输字节
            System.out.println("transform消耗时间"+(System.currentTimeMillis()-transform));
            log.info("transform消耗时间"+(System.currentTimeMillis()-transform));

            long getObj=System.currentTimeMillis();
            InputStream fileInputStream = new ByteArrayInputStream(fileBytes);
            ResourceInfo resourceInfo = (ResourceInfo) strogeService.getObject(s, ResourceInfo.class);
            System.out.println("getObj消耗时间"+(System.currentTimeMillis()-getObj));

            long rewrite=System.currentTimeMillis();
            String filePath = FileUtil.creatRandomNameFile(
                    System.getProperty("user.dir") + "/data/downloadTmp/", resourceInfo.getFileSuffix());

            OutputStream fileOutputStream = new FileOutputStream(new File(filePath));
            FileUtil.inputStreamWriteToOutputStream(fileInputStream, fileOutputStream);
            System.out.println("rewrite消耗时间"+(System.currentTimeMillis()-rewrite));

            //写入完成之后将数据拼成可访问的链接
            String url = tools.getUrl() + "/upload" + filePath.substring(filePath.lastIndexOf("/"));
            newPicList.add(url);
        }
        return newPicList;
    }
}
