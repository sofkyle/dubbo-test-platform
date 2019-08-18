package com.kc.dtp.controller;

import com.kc.dtp.bean.ProviderDetailVO;
import com.kc.dtp.bean.ProviderVO;
import com.kc.dtp.bean.ApiVO;
import com.kc.dtp.discovery.bean.ProviderConfig;
import com.kc.dtp.exception.ServiceNotFoundException;
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
        try {
            apiService.getAllByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }

    @PostMapping(value = "/search")
    public String searchApi(ApiVO apiVO, final Model model) {
        try {
            String serviceName = apiVO.getApiName().trim();
            List<ProviderConfig> providerConfigList = apiService.getProviderByServiceName(serviceName).block();

            ProviderVO providerVO = ProviderVO.builder().build();
            providerVO.setServiceName(serviceName);
            List<ProviderDetailVO> providerDetailVOList = new LinkedList<>();
            providerConfigList.stream().forEach(e -> {
                ProviderDetailVO providerDetailVO = ProviderDetailVO.builder()
                        .host(e.getHost())
                        .port(e.getPort())
                        .methods(e.getMethods()).build();
                providerDetailVOList.add(providerDetailVO);
            });
            providerVO.setProviderDetailVOList(providerDetailVOList);
            apiVO.setProviderVO(providerVO);
        } catch (ServiceNotFoundException snfe) {
            snfe.printStackTrace();
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("apiVO", apiVO);

        return "service";
    }

    @PostMapping(value = "/search")
    @ResponseBody
    public String invokeService(ApiVO apiVO, final Model model) {

        return null;
    }
}
