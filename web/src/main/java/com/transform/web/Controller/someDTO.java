package com.transform.web.Controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class someDTO {
    private Long id;
    private Long blockrangeId;
    private String patrolName;
    private String userName;
    private Date createTime;
    private Date updeteTime;
    private String question;
    private String opinion;
    private String geometry;
    private List<String> imagesUrl;
    private List<String> imageIds;
    private List<String> audioIds;
}
