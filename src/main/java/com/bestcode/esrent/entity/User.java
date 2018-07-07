package com.bestcode.esrent.entity;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;

/**
 * @author xch
 * @create 2018-07-07 22:32
 **/
@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String password;
    private Integer status;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "last_login_time")
    private Date lastLoginTime;
    @Column(name = "last_update_time")
    private Date lastUpdateTime;
    private String avatar;
}
