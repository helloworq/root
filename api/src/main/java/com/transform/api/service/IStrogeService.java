package com.transform.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * 此接口负责有关文件上传和下载的功能
 */
public interface IStrogeService {

    String uploadFile(File file,String uploadFilePath);

    String uploadMulitFile(MultipartFile[] list, String uploadFilePath);

    String downloadFile(String filePath);
}
