package com.transform.api.model.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserMomentInfoDTO implements Serializable {
    private String id;

    private String uuid;

    private String momentContentWords;

    private Date momentSendTime;

    private String userDevice;

    private String isEdit;

    private String collectCount;

    private String shareCount;

    private String commentCount;

    private String likeCount;

    private String picIds;
}
