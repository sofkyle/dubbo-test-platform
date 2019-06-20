package com.kc.dtp.controller;

import com.kc.dtp.handler.ApiHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Kyle
 */
@RestController
@RequestMapping("/view")
public class IndexController {

    @Resource
    private ApiHandler apiHandler;

    @RequestMapping("/index")
    public String listPage(final Model model) {
        // final Mono<Integer> api = apiHandler.addApi("url");
        return "view/index";
    }
}
