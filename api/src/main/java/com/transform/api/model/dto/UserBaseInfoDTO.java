package com.transform.api.model.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserBaseInfoDTO implements Serializable {
    private String userWechatNum;

    private String userQQNum;

    private String userPhoneNum;

    private String userEmail;

    private String userBlogLink;
}
