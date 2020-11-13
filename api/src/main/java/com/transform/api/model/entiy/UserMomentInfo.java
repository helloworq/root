package com.transform.api.model.entiy;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_usermomentinfo")
@Data
public class UserMomentInfo implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "uuid", nullable = false, length = 64)
    private String uuid;

    @Column(name = "momentContentWords", nullable = false, length = 1024)
    private String momentContentWords;

    @Column(name = "momentSendTime", nullable = false, length = 64)
    private Date momentSendTime;

    @Column(name = "userDevice",  length = 64)
    private String userDevice;

    @Column(name = "isEdit",  length = 64)
    private String isEdit;

    @Column(name = "collectCount",  length = 64)
    private String collectCount;

    @Column(name = "shareCount",  length = 64)
    private String shareCount;

    @Column(name = "commentCount",  length = 64)
    private String commentCount;

    @Column(name = "likeCount",  length = 64)
    private String likeCount;

    @Column(name = "picIds", nullable = false, length = 1024)
    private String picIds;
}





