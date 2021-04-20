package com.transform.api.model.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserMomentInfoDTO implements Serializable {
    public UserMomentInfoDTO(){}

    public UserMomentInfoDTO(Date momentSendTime, String userDevice, String isEdit, String collectCount, String shareCount, String commentCount, String likeCount) {
        this.momentSendTime = momentSendTime;
        this.userDevice = userDevice;
        this.isEdit = isEdit;
        this.collectCount = collectCount;
        this.shareCount = shareCount;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
    }

    public void initCountValue(){
        this.setCollectCount("0");
        this.setLikeCount("0");
        this.setShareCount("0");
        this.setCommentCount("0");
    }
    private String id;

    private String uuid;

    private String name;

    private String momentContentWords;

    private Date momentSendTime;

    private String userDevice;

    private String isEdit;

    private String collectCount;

    private String shareCount;

    private String commentCount;

    private String likeCount;

    private List<String> picIds;

    private String headIconUrl;
}
