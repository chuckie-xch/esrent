package com.bestcode.esrent.controller.house;

import com.bestcode.esrent.base.ApiResponse;
import com.bestcode.esrent.entity.dto.SupportAddressDTO;
import com.bestcode.esrent.service.SupportAddressService;
import com.bestcode.esrent.service.base.ServiceMultiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xch
 * @create 2018-07-18 21:59
 **/
@Controller
public class HouseController {

    @Autowired
    private SupportAddressService supportAddressService;

    @GetMapping("/address/support/cities")
    @ResponseBody
    public ApiResponse getSupportCities() {
        ServiceMultiResult<SupportAddressDTO> result = supportAddressService.findAllCities();
        if (result.getResultSize() == 0) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }
        return ApiResponse.ofSuccess(result.getResult());
    }

    /**
     * 获取对应城市支持的区域列表
     *
     * @param cityEnName
     * @return
     */
    @GetMapping("/address/support/regions")
    @ResponseBody
    public ApiResponse getSupportRegions(@RequestParam(name = "city_name") String cityEnName) {
        ServiceMultiResult<SupportAddressDTO> addressResult = supportAddressService.findAllRegionsByCityName
                (cityEnName);
        if (addressResult.getResult() == null || addressResult.getTotal() < 1) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }
        return ApiResponse.ofSuccess(addressResult);
    }

    /**
     * 获取具体城市所支持的地铁线路
     *
     * @param cityEnName
     * @return
     */
    @GetMapping("address/support/subway/line")
    @ResponseBody
    public ApiResponse getSupportSubway(@RequestParam(name = "city_name") String cityEnName) {
        return null;
    }
}
