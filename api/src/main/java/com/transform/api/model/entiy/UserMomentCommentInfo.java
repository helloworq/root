package com.transform.api.model.entiy;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_usermonmentcommentinfo")
@Data
public class UserMomentCommentInfo implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "momentId", unique = true, nullable = false, length = 64)
    private String momentId;

    @Column(name = "commentContent", unique = true, nullable = false, length = 64)
    private String commentContent;

    @Column(name = "whoComment", unique = true, nullable = false, length = 64)
    private String whoComment;

    @Column(name = "commentTime", unique = true, nullable = false, length = 64)
    private Date commentTime;
}
