package com.bestcode.esrent.service;

import java.util.List;

import com.bestcode.esrent.entity.SupportAddress;
import com.bestcode.esrent.entity.dto.SupportAddressDTO;
import com.bestcode.esrent.service.base.ServiceMultiResult;

/**
 * @author xch
 * @create 2018-07-18 22:10
 **/
public interface SupportAddressService {

    ServiceMultiResult<SupportAddressDTO> findAllCities();
}
