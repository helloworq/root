package com.transform.api.model.entiy;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_usermonmentlikeinfo")
@Data
public class UserMonmentLikeInfo {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "momentId", unique = true, nullable = false, length = 64)
    private String momentId;

    @Column(name = "whoLike", unique = true, nullable = false, length = 64)
    private String whoLike;

    @Column(name = "likeTime", unique = true, nullable = false, length = 64)
    private String likeTime;

}
