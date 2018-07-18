package com.bestcode.esrent.repository;

import java.util.List;

import com.bestcode.esrent.entity.SupportAddress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xch
 * @create 2018-07-18 22:06
 **/
public interface SupportAddressRepository extends JpaRepository<SupportAddress, Long> {

    /**
     * 获取所有对应城镇级别的信息
     *
     * @return
     */
    List<SupportAddress> findAllByLevel(String level);
}
