package com.bestcode.esrent.entity;

import javax.persistence.*;

import lombok.Data;

/**
 * @author xch
 * @create 2018-07-16 23:38
 **/
@Table(name ="role")
@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_id")
    private Long userId;
    private String name;
}
