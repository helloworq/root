package com.transform.api.model.entiy;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_usermonmentcollectinfo")
@Data
public class UserMomentCollectInfo implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "momentId", unique = true, nullable = false, length = 64)
    private String momentId;

    @Column(name = "whoCollect", unique = true, nullable = false, length = 64)
    private String whoCollect;

    @Column(name = "collectTime", unique = true, nullable = false, length = 64)
    private Date collectTime;
}
