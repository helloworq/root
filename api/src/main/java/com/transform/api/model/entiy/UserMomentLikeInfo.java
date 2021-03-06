package com.transform.api.model.entiy;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_usermonmentlikeinfo")
@Data
public class UserMomentLikeInfo implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "momentId", nullable = false, length = 64)
    private String momentId;

    @Column(name = "whoLike",  nullable = false, length = 64)
    private String whoLike;

    @Column(name = "likeTime",  nullable = false, length = 64)
    private Date likeTime;

}
