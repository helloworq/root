package com.transform.api.model.dto;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
public class UserRelationDTO implements Serializable {

    private String id;

    public UserRelationDTO(String uuidUserA, String uuidUserB, Integer relationStatus, Date operTime) {
        this.uuidUserA = uuidUserA;
        this.uuidUserB = uuidUserB;
        this.relationStatus = relationStatus;
        this.operTime = operTime;
    }

    //操作人
    private String uuidUserA;

    //被执行人
    private String uuidUserB;

    //关系状态，follow关注为1，unfriend屏蔽为2，block拉黑为3，无关系则直接删除记录
    private Integer relationStatus;

    private Date operTime;
}
