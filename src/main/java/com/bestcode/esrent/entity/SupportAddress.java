package com.bestcode.esrent.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;

/**
 * @author xch
 * @create 2018-07-18 22:00
 **/
@Entity
@Table(name = "support_address")
@Data
public class SupportAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 上一级行政单位
    @Column(name = "belong_to")
    private String belongTo;

    @Column(name = "en_name")
    private String enName;

    @Column(name = "cn_name")
    private String cnName;

    private String level;

//    @Column(name = "baidu_map_lng")
//    private double baiduMapLongitude;
//
//    @Column(name = "baidu_map_lat")
//    private double baiduMapLatitude;

    @Getter
    public enum Level {
        CITY("city"),
        REGION("region"),;
        private String value;

        Level(String value) {
            this.value = value;
        }
    }
}
