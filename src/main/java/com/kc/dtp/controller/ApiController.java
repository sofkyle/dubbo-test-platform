package com.kc.dtp.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.kc.dtp.bean.vo.ApiVO;
import com.kc.dtp.bean.vo.InterfaceVO;
import com.kc.dtp.common.InterfaceParser;
import com.kc.dtp.common.ProviderService;
import com.kc.dtp.service.ApiService;
import org.apache.dubbo.common.URL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    public String searchApi(ApiVO apiVO, final Model model) {
        model.addAttribute("apiVO", apiVO);
        String protocol = apiVO.getProtocol();
        String address = apiVO.getAddress();
        String group = apiVO.getGroup();

        List<String> interfaceList = ProviderService.get(address).getProviders(protocol, address, group);

        List<InterfaceVO> interfaceVOList = new LinkedList<>();
        for (String itf : interfaceList) {
            interfaceVOList.add(InterfaceVO.builder().name(itf).build());
        }

        model.addAttribute("address", address);
        model.addAttribute("interfaceList", interfaceVOList);

        return "service";
    }

    @GetMapping(value = "/method/list")
    @ResponseBody
    public String listMethod(ApiVO apiVO) {
        String address = apiVO.getAddress();
        String serviceName = apiVO.getServiceName();

        ProviderService providerService = ProviderService.get(address);
        Map<String, URL> provider = providerService.findUrlByServiceName(serviceName);
        URL url = Lists.newArrayList(provider.values()).get(0);
        InterfaceVO interfaceVO = InterfaceParser.parseUrl(url);

        return JSON.toJSONString(interfaceVO.getMethods());
    }

    @PostMapping(value = "/invoke")
    @ResponseBody
    public String invokeService(ApiVO apiVO) {

        return null;
    }
}
