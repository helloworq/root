package com.transform.api.model.entiy.mongo;

import lombok.Data;

import javax.persistence.Id;

@Data
public class ResourceInfo {
    @Id
    //@ApiModelProperty("资源文件id")
    private String id;
    //@ApiModelProperty("资源文件名")
    private String fileName;
    //@ApiModelProperty("资源文件本地路径")
    private String filePath;
    //@ApiModelProperty("资源文件后缀")
    private String fileSuffix;
    //@ApiModelProperty("资源文件时长")
    private String audioTime;
}
