package com.kc.dtp.controller;

import com.kc.dtp.handler.ApiHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Kyle
 */
@Controller
public class IndexController {

    @Resource
    private ApiHandler apiHandler;

    @GetMapping("/")
    public String listPage(final Model model) {
        model.addAttribute("api", "");
        return "index";
    }
}
