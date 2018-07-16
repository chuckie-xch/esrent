package com.bestcode.esrent.controller;

import com.bestcode.esrent.base.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xch
 * @create 2018-07-07 23:37
 **/
@Controller
public class HomeController {

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("name","hahah");
        return "index";
    }

    @GetMapping("/get")
    @ResponseBody
    public ApiResponse get() {
        return ApiResponse.ofMessage(200, "成功了");
    }
}
