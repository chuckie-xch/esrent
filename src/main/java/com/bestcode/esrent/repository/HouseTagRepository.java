package com.bestcode.esrent.repository;

import java.util.List;

import com.bestcode.esrent.entity.HouseTag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xch
 * @create 2018-07-30 23:11
 **/
public interface HouseTagRepository extends JpaRepository<HouseTag, Long> {

    List<HouseTag> findAllByHouseId(Long houseId);
}
