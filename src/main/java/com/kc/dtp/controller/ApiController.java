package com.kc.dtp.controller;

import com.kc.dtp.model.UserApi;
import com.kc.dtp.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Kyle
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping(value = "/list")
    public String list(@ModelAttribute Long userId) {
        try {
            apiService.getAllByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }

    @PostMapping(value = "/add")
    public String addApi(UserApi userApi, final Model model) {
        System.out.println(userApi.getApiUrl());
        model.addAttribute("api", userApi.getApiUrl());
        return "index";
    }
}
