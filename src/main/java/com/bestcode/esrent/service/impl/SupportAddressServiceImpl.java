package com.bestcode.esrent.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bestcode.esrent.entity.SupportAddress;
import com.bestcode.esrent.entity.dto.SupportAddressDTO;
import com.bestcode.esrent.repository.SupportAddressRepository;
import com.bestcode.esrent.service.SupportAddressService;
import com.bestcode.esrent.service.base.ServiceMultiResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author xch
 * @create 2018-07-18 22:14
 **/
@Service
public class SupportAddressServiceImpl implements SupportAddressService {

    @Autowired
    private SupportAddressRepository supportAddressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ServiceMultiResult<SupportAddressDTO> findAllCities() {
        List<SupportAddress> list = supportAddressRepository.findAllByLevel(SupportAddress.Level.CITY.getValue());
        List<SupportAddressDTO> supportAddressDTOS = new ArrayList<>();
        for (SupportAddress supportAddress : list) {
            SupportAddressDTO target = modelMapper.map(supportAddress, SupportAddressDTO.class);
            supportAddressDTOS.add(target);
        }
        return new ServiceMultiResult<>(supportAddressDTOS.size(), supportAddressDTOS);
    }

    @Override
    public ServiceMultiResult<SupportAddressDTO> findAllRegionsByCityName(String cityEnName) {
        if (StringUtils.isEmpty(cityEnName)) {
            return new ServiceMultiResult<>(0, null);
        }
        List<SupportAddress> regions = supportAddressRepository.findAllByLevelAndBelongTo(SupportAddress.Level.REGION
                .getValue(), cityEnName);
        List<SupportAddressDTO> result = new ArrayList<>();
        for (SupportAddress supportAddress : regions) {
            result.add(modelMapper.map(supportAddress, SupportAddressDTO.class));
        }
        return new ServiceMultiResult<>(result.size(), result);
    }
}
