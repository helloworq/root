package com.transform.api.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class UserInfoDTO implements Serializable {
    private String id;

    private String userName;

    private Date userJoinTime;

    private String userAge;

    private String userSex;

    private String userHeadUrl;

    private String userWechatNum;

    private String userQQNum;

    private String userPhoneNum;

    private String userEmail;

    private String userBlogLink;
}
