package com.kc.dtp.controller;

import com.kc.dtp.bean.ProviderDetailVO;
import com.kc.dtp.bean.ProviderVO;
import com.kc.dtp.bean.UserApiVO;
import com.kc.dtp.discovery.bean.ProviderConfig;
import com.kc.dtp.exception.ServiceNotFoundException;
import com.kc.dtp.model.UserApi;
import com.kc.dtp.service.ApiService;
import org.apache.zookeeper.KeeperException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String searchApi(UserApiVO userApiVO, final Model model) {
        try {
            String serviceName = userApiVO.getApiName();
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
            userApiVO.setProviderVO(providerVO);
        } catch (ServiceNotFoundException snfe) {
            snfe.printStackTrace();
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userApiVO", userApiVO);

        return "service";
    }
}
