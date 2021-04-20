package com.transform.api.model.dto;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserMomentCommentInfoDTO implements Serializable {

    private String id;

    private String momentId;

    private String commentContent;

    private String whoComment;

    private Date commentTime;

    private String headIconUrl;
}
