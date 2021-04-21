package com.transform.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.transform.api.model.entiy.mongo.ResourceInfo;
import com.transform.api.service.IStrogeService;
import com.transform.base.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@Component
public class StrogeServiceImpl implements IStrogeService {
    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 上传缓存文件
     *
     * @param fileTempPath
     * @return
     */
    @Override
    public String uploadTempFile(String fileTempPath) {
        String path = null;
        try {
            File file = new File(fileTempPath);
            FileInputStream fileInputStream = new FileInputStream(file);
            //向Girdfs存储文件
            ObjectId objectId = gridFsTemplate.store(fileInputStream, UUID.randomUUID().toString().replace("-", ""));
            path = objectId.toString();
            fileInputStream.close();
            return path;
        } catch (Exception e) {
            System.out.println("文件读取失败");
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 下载存储在mongo库里的文件
     *
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public byte[] getMongoFileBytes(String id) throws IOException {

        //根据id查询文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));
        //打开流下载对象
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //获取流对象
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, downloadStream);
        InputStream input = gridFsResource.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        FileUtil.inputStreamWriteToOutputStream(input, baos);

        downloadStream.close();
        input.close();
        baos.close();
        return baos.toByteArray();
    }

    @Override
    public String createSingleTempFileByMongo(String picId) {
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(picId)));

        if (Objects.nonNull(gridFSFile)) {
            ResourceInfo resourceInfo = mongoTemplate.findById(picId, ResourceInfo.class);
            try {
                String filePath = FileUtil.creatRandomNameFile(
                        System.getProperty("user.dir") + "/data/downloadTmp/", resourceInfo.getFileSuffix());
                FileOutputStream outputStream = new FileOutputStream(new File(filePath));
                gridFSBucket.downloadToStream(gridFSFile.getObjectId(), outputStream);

                outputStream.close();
                return filePath;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 删除mongo库存储的文件以及存储的文件信息
     *
     * @param id
     * @return
     */
    @Override
    public String deleteMongoFile(String id) {
        //根据文件id删除fs.files和fs.chunks中的记录
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(id)));
        //删除Rsource表里存的信息
        mongoTemplate.remove(mongoTemplate.findById(id, ResourceInfo.class));
        return "success delete";
    }

    /**
     * 保存实体类信息
     *
     * @param entiy
     * @return
     */
    @Override
    public String save(Object entiy) {
        mongoTemplate.save(entiy);
        return "success save";
    }

    /**
     * 获取实体类信息，获取时需要指定类的类型
     *
     * @param id
     * @param type
     * @return
     */
    @Override
    public Object getObject(String id, Class type) {
        return mongoTemplate.findById(id, type);
    }
}
