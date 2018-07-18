package com.bestcode.esrent.controller.house;

import com.bestcode.esrent.base.ApiResponse;
import com.bestcode.esrent.entity.dto.SupportAddressDTO;
import com.bestcode.esrent.service.SupportAddressService;
import com.bestcode.esrent.service.base.ServiceMultiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
