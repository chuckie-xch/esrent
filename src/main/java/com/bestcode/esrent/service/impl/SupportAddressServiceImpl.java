package com.bestcode.esrent.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bestcode.esrent.entity.Subway;
import com.bestcode.esrent.entity.SubwayStation;
import com.bestcode.esrent.entity.SupportAddress;
import com.bestcode.esrent.entity.dto.SubwayDTO;
import com.bestcode.esrent.entity.dto.SubwayStationDTO;
import com.bestcode.esrent.entity.dto.SupportAddressDTO;
import com.bestcode.esrent.repository.SubwayRepository;
import com.bestcode.esrent.repository.SubwayStationRepository;
import com.bestcode.esrent.repository.SupportAddressRepository;
import com.bestcode.esrent.service.SupportAddressService;
import com.bestcode.esrent.service.base.ServiceMultiResult;
import com.sun.org.apache.regexp.internal.REUtil;
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
    private SubwayStationRepository subwayStationRepository;

    @Autowired
    private SubwayRepository subwayRepository;

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

    @Override
    public List<SubwayDTO> findAllSubwayByCity(String cityEnName) {
        List<SubwayDTO> result = new ArrayList<>();
        List<Subway> subways = subwayRepository.findAllByCityEnName(cityEnName);
        if (subways.isEmpty()) {
            return result;
        }
        result = subways.stream().map(subway -> modelMapper.map(subway, SubwayDTO.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<SubwayStationDTO> findAllStationBySubway(Long subwayId) {
        List<SubwayStationDTO> result = new ArrayList<>();
        List<SubwayStation> stations = subwayStationRepository.findAllBySubwayId(subwayId);
        if (stations.isEmpty()) {
            return result;
        }
        stations.forEach(station -> result.add(modelMapper.map(station, SubwayStationDTO.class)));
        return result;
    }
}
