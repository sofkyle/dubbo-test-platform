package com.kc.dtp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: Kyle
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String listPage(final Model model) {
        model.addAttribute("api", "");
        return "index";
    }
}
