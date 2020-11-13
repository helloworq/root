package com.transform.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 此接口负责有关文件上传和下载的功能
 */
public interface IStrogeService {

    /**
     * 上传本地缓存中的文件
     * @param fileTempPath
     * @return
     */
    String uploadTempFile(String fileTempPath);

    /**
     * 根据上传后返回的文件标识id下载文件字节
     * @param id
     * @return
     */
    byte[] getMongoFileBytes(String id) throws IOException;

    /**
     * 根据id删除mongo中的文件信息以及数据
     * @param id
     * @return
     */
    String deleteMongoFile(String id);

    /**
     * 保存实体数据
     * @param entiy
     * @return
     */
    String save(Object entiy);

    /**
     *
     * @param id
     * @param type
     * @return
     */
    Object getObject(String id,Class type);
}
