package com.kc.dtp.controller;

import com.alibaba.fastjson.JSON;
import com.kc.dtp.discovery.Discovery;
import com.kc.dtp.discovery.bean.ZKDataHolder;
import com.kc.dtp.model.UserApi;
import com.kc.dtp.service.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author: Kyle
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @Resource
    private ApiService apiService;

    @Resource
    private Discovery<ZKDataHolder> discovery;

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
        try {
            String serviceName = userApi.getApiUrl();
            String content = JSON.toJSONString(discovery.readRoot(serviceName));
            model.addAttribute("content", content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("api", userApi.getApiUrl());
        return "index";
    }
}
