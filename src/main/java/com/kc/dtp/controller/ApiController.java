package com.kc.dtp.controller;

import com.kc.dtp.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
        // apiService.getAllByUserId(userId);
        return "list";
    }

    @PostMapping(value = "/add")
    public String addApi(@ModelAttribute String apiTxt, final Model model) {
        System.out.println(apiTxt);
        model.addAttribute("api", apiTxt);
        return "index";
    }
}
