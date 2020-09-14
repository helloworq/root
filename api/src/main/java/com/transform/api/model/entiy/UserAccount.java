package com.transform.api.model.entiy;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_useraccount")
@Data
public class UserAccount implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "userAccount", unique = true, nullable = false, length = 64)
    private String userAccount;

    @Column(name = "userPassword", nullable = false, length = 64)
    private String userPassword;

}
