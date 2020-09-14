package com.transform.api.model.entiy.mongo;

import lombok.Data;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class ResourceInfo implements Serializable {
    @Id
    //@ApiModelProperty("资源文件id")
    private String id;
    //@ApiModelProperty("资源文件名")
    private String fileName;
    //@ApiModelProperty("资源文件后缀")
    private String fileSuffix;
    //@ApiModelProperty("资源文件大小")
    private String fileSize;
    //@ApiModelProperty("资源文件大小")
    private String fileTempPath;
}
