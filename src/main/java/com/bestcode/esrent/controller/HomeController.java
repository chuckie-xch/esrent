package com.bestcode.esrent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
