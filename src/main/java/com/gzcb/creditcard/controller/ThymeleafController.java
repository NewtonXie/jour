package com.gzcb.creditcard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
@RequestMapping("/th")
public class ThymeleafController {
    @RequestMapping("/")
    public String toIndex() {
        return "user/add_application";
    }

    @RequestMapping("/toIndexModel")
    public String toIndexModel(Map<String,Object> map){
        map.put("name","ODS");
        return "index";
    }
}
