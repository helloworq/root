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

    @Column(name = "momentId", nullable = false, length = 64)
    private String momentId;

    @Column(name = "commentContent",  nullable = false, length = 64)
    private String commentContent;

    @Column(name = "whoComment", nullable = false, length = 64)
    private String whoComment;

    @Column(name = "commentTime", nullable = false, length = 64)
    private Date commentTime;
}
