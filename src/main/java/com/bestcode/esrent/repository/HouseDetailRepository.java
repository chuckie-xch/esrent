package com.bestcode.esrent.repository;

import com.bestcode.esrent.entity.HouseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xch
 * @create 2018-07-30 23:09
 **/
public interface HouseDetailRepository extends JpaRepository<HouseDetail, Long> {

    HouseDetail findByHouseId(Long houseId);
}
