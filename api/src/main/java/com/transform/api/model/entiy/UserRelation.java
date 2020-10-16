package com.transform.api.model.entiy;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_userrelation")
@Data
public class UserRelation implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    public UserRelation(String uuidUserA, String uuidUserB, Integer relationStatus, Date operTime) {
        this.uuidUserA = uuidUserA;
        this.uuidUserB = uuidUserB;
        this.relationStatus = relationStatus;
        this.operTime = operTime;
    }

    //操作人
    @Column(name = "uuidUserA", unique = true, nullable = false, length = 64)
    private String uuidUserA;

    //被执行人
    @Column(name = "uuidUserB", unique = true, nullable = false, length = 64)
    private String uuidUserB;

    //关系状态，follow关注为1，unfriend屏蔽为2，block拉黑为3，无关系则直接删除记录
    @Column(name = "relationStatus", unique = true, nullable = false, length = 64)
    private Integer relationStatus;

    @Column(name = "operTime", unique = true, nullable = false, length = 64)
    private Date operTime;
}
