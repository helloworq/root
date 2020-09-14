package com.transform.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.transform.api.model.entiy.mongo.ResourceInfo;
import com.transform.api.service.IStrogeService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.*;
import java.util.UUID;

@Service
public class StrogeServiceImpl implements IStrogeService {
    @Autowired
    GridFsTemplate gridFsTemplate;
    @Autowired
    GridFSBucket gridFSBucket;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public String uploadTempFile(String fileTempPath){
        String path=null;
        try {
            File file = new File(fileTempPath);
            FileInputStream fileInputStream = new FileInputStream(file);
            //向Girdfs存储文件
            ObjectId objectId = gridFsTemplate.store(fileInputStream, UUID.randomUUID().toString().replace("-",""));
            path=objectId.toString();
            fileInputStream.close();
            return path;
        } catch (Exception e) {
            System.out.println("文件读取失败");
            e.printStackTrace();
        }
        return path;
    }

    @Override
    public byte[] getMongoFileInputStream(String id) throws IOException {
        //根据id查询文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));
        //打开流下载对象
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //获取流对象
        GridFsResource gridFsResource=new GridFsResource(gridFSFile,downloadStream);
        InputStream input=gridFsResource.getInputStream();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int ch;
        while (-1 != (ch = input.read())){
            baos.write(ch);
        }
        byte[] res=baos.toByteArray();

        downloadStream.close();
        input.close();
        baos.close();
        return res;
    }

    @Override
    public String deleteMongoFile(String id) {
        //根据文件id删除fs.files和fs.chunks中的记录
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(id)));
        mongoTemplate.remove(mongoTemplate.findById(id, ResourceInfo.class));
        return "success delete";
    }

    @Override
    public String save(Object entiy) {
        mongoTemplate.save(entiy);
        return "success save";
    }
}
