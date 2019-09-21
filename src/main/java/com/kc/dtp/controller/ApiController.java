package com.kc.dtp.controller;

import com.kc.dtp.bean.vo.ApiVO;
import com.kc.dtp.bean.vo.InterfaceVO;
import com.kc.dtp.common.ProviderService;
import com.kc.dtp.service.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: Kyle
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @Resource
    private ApiService apiService;

    @GetMapping(value = "/list")
    public String list(@ModelAttribute Long userId) {

        return "list";
    }

    @PostMapping(value = "/search")
    public void searchApi(ApiVO apiVO, final Model model) {
        model.addAttribute("apiVO", apiVO);
        String protocol = apiVO.getProtocol();
        String address = apiVO.getAddress();
        String group = apiVO.getGroup();

        List<String> interfaceList = ProviderService.get(address).getProviders(protocol, address, group);

        List<InterfaceVO> interfaceVOList = new LinkedList<>();
        for (String itf : interfaceList) {
            interfaceVOList.add(InterfaceVO.builder().name(itf).build());
        }

        model.addAttribute("interfaceList", interfaceVOList);
    }

    @GetMapping(value = "/getmethods")
    @ResponseBody
    public void getMethods(ApiVO apiVO, final Model model) {

        model.addAttribute("methodList", new LinkedList<>());
    }

    @PostMapping(value = "/invoke")
    @ResponseBody
    public String invokeService(ApiVO apiVO, final Model model) {

        return null;
    }
}
