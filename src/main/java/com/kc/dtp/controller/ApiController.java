package com.kc.dtp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Kyle
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @RequestMapping(value = "/add")
    public String addApi(@RequestParam(value = "apiTxt")String apiTxt, final Model model) {
        System.out.println(apiTxt);
        model.addAttribute("api", apiTxt);
        return "index";
    }
}
