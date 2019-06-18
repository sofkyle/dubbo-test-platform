package com.kc.dtp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Kyle
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String listPage(final Model model) {
        return "index";
    }
}
