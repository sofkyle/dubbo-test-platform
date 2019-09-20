package com.kc.dtp.controller;

import com.kc.dtp.service.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * @author: Kyle
 */
@Controller
public class IndexController {
    @Resource
    private ApiService apiService;

    @GetMapping("/")
    public String listPage(final Model model) {
        return "index";
    }
}
