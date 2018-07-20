package com.bestcode.esrent.repository;

import java.util.List;

import com.bestcode.esrent.entity.Subway;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xch
 * @create 2018-07-19 20:26
 **/
public interface SubwayRepository extends JpaRepository<Subway, Long> {

    /**
     * 获取城市下的地铁线路
     *
     * @param cityEnName
     * @return
     */
    List<Subway> findAllByCityEnName(String cityEnName);
}
