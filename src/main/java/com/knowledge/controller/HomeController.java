package com.knowledge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 根路径控制器：访问项目根地址时跳转到登录页
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String root(){
        return "redirect:/user/toLogin";
    }
}
