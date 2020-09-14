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

    @Column(name = "momentContentWords", nullable = false, length = 64)
    private String momentContentWords;

    @Column(name = "momentSendTime", nullable = false, length = 64)
    private Date momentSendTime;

    @Column(name = "userDevice", nullable = false, length = 64)
    private String userDevice;

    @Column(name = "isEdit", nullable = false, length = 64)
    private String isEdit;

    @Column(name = "collectCount", nullable = false, length = 64)
    private String collectCount;

    @Column(name = "shareCount", nullable = false, length = 64)
    private String shareCount;

    @Column(name = "commentCount", nullable = false, length = 64)
    private String commentCount;

    @Column(name = "likeCount", nullable = false, length = 64)
    private String likeCount;

    @Column(name = "picIds", nullable = false, length = 1024)
    private String picIds;
}





