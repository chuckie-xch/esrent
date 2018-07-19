package com.bestcode.esrent.entity;

import javax.persistence.*;

import lombok.Data;

/**
 * @author xch
 * @create 2018-07-19 20:23
 **/
@Data
@Table(name = "subway")
@Entity
public class Subway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "city_en_name")
    private String cityEnName;
}
