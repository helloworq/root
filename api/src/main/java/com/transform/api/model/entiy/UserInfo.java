package com.transform.api.model.entiy;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_userinfo")
@Data
public class UserInfo implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "userName", nullable = false, length = 64)
    private String userName;

    @Column(name = "userJoinTime", nullable = false, length = 64)
    private Date userJoinTime;

    @Column(name = "userAge", nullable = false, length = 64)
    private String userAge;

    @Column(name = "userSex", nullable = false, length = 64)
    private String userSex;

    @Column(name = "userHeadUrl", nullable = false, length = 64)
    private String userHeadUrl;

    @Column(name = "userWechatNum", nullable = false, length = 64)
    private String userWechatNum;

    @Column(name = "userQQNum", nullable = false, length = 64)
    private String userQQNum;

    @Column(name = "userPhoneNum", nullable = false, length = 64)
    private String userPhoneNum;

    @Column(name = "userEmail", nullable = false, length = 64)
    private String userEmail;

    @Column(name = "userBlogLink", nullable = false, length = 64)
    private String userBlogLink;
}
