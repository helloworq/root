package com.transform.service.serviceimpl;

import com.transform.api.service.IStrogeService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class StrogeServiceImpl implements IStrogeService {

    public String uploadFile(MultipartFile[] list, String uploadFilePath) {
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
        return null;
    }

    @Override
    public String uploadFile(File file, String uploadFilePath) {
        return null;
    }

    @Override
    public String uploadMulitFile(MultipartFile[] list, String uploadFilePath) {
        return null;
    }

    @Override
    public String downloadFile(String uploadFilePath) {
        return null;
    }
}
