package com.bestcode.esrent.entity;

import javax.persistence.*;

import lombok.Data;

/**
 * @author xch
 * @create 2018-07-19 21:58
 **/
@Entity
@Data
@Table(name = "subway_station")
public class SubwayStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subway_id")
    private Long subwayId;

    private String name;
}
