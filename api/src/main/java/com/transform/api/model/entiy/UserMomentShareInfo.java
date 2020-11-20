package com.transform.api.model.entiy;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_usermonmentshareinfo")
@Data
public class UserMomentShareInfo implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "momentId",  nullable = false, length = 64)
    private String momentId;

    @Column(name = "whoShare", nullable = false, length = 64)
    private String whoShare;

    @Column(name = "shareTime", nullable = false, length = 64)
    private Date shareTime;
}
