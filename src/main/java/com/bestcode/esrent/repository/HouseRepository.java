package com.bestcode.esrent.repository;

import com.bestcode.esrent.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xch
 * @create 2018-07-30 22:42
 **/
public interface HouseRepository extends JpaRepository<House, Long> {
}
