package com.bestcode.esrent.repository;

import java.util.List;

import com.bestcode.esrent.entity.SubwayStation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xch
 * @create 2018-07-19 22:01
 **/
public interface SubwayStationRepository extends JpaRepository<SubwayStation, Long> {

    List<SubwayStation> findAllBySubwayId(Long subwayId);
}
