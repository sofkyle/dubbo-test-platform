package com.kc.dtp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.kc.dtp.bean.vo.ApiVO;
import com.kc.dtp.bean.vo.InterfaceVO;
import com.kc.dtp.bean.vo.ParamVO;
import com.kc.dtp.common.ApplicationConfigInstance;
import com.kc.dtp.common.InterfaceParser;
import com.kc.dtp.common.ProviderService;
import com.kc.dtp.service.ApiService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        String protocol = apiVO.getRegisterProtocol();
        String address = apiVO.getAddress();
        String registryGroup = apiVO.getRegistryGroup();

        List<String> interfaceList = ProviderService.get(address).getProviders(protocol, address, registryGroup);

        if (CollectionUtils.isEmpty(interfaceList)) {
            model.addAttribute("errMsg", "服务不存在");
            return "404";
        }

        List<InterfaceVO> interfaceVOList = new LinkedList<>();
        for (String itf : interfaceList) {
            interfaceVOList.add(InterfaceVO.builder().name(itf).build());
        }

        model.addAttribute("protocol", protocol);
        model.addAttribute("address", address);
        model.addAttribute("interfaceList", interfaceVOList);
        model.addAttribute("registryGroup", registryGroup);

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

    @GetMapping(value = "/method/invoke")
    @ResponseBody
    public String invokeService(ServerWebExchange exchange) {
        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
        String protocol = queryParams.get("protocol").get(0);
        String address = queryParams.get("address").get(0);
        String serviceName = queryParams.get("serviceName").get(0);
        String registryGroup = queryParams.get("registryGroup").get(0);
        List<String> paramListStr = queryParams.get("paramList[]");
        List<ParamVO> paramList = null;
        String[] typeList = null;
        Object[] valueList = null;
        if (!CollectionUtils.isEmpty(paramListStr)) {
            paramList = paramListStr.stream()
                    .map(param -> JSONObject.parseObject(param, new TypeReference<ParamVO>() {}))
                    .collect(Collectors.toList());
            typeList = new String[paramList.size()];
            typeList = paramList.stream()
                    .map(paramVO -> paramVO.getType())
                    .collect(Collectors.toList()).toArray(typeList);
            valueList = new String[paramList.size()];
            valueList = paramList.stream()
                    .map(paramVO -> paramVO.getValue())
                    .collect(Collectors.toList()).toArray(valueList);
        }
        String methodName = queryParams.get("methodName").get(0);
        String methodGroup = methodName.substring(0, methodName.indexOf("/") + 1);
        if (!methodGroup.isEmpty()) {
            methodName = methodName.substring(methodName.indexOf("/"));
        }

        ReferenceConfig<GenericService> ref = new ReferenceConfig<GenericService>();
        // 1.1 应用名称
        ref.setApplication(ApplicationConfigInstance.get());

        // 1.2 注册中心配置
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol(protocol);
        registryConfig.setAddress(address);
        registryConfig.setGroup(registryGroup);
        ref.setRegistry(registryConfig);

        // 1.3 接口名称
        ref.setProtocol("dubbo");
        ref.setInterface(serviceName);
        if (!methodGroup.isEmpty()) {
            ref.setGroup(methodName);
        }

        // 1.4 泛化标识
        ref.setGeneric(true);

        // 2 获取远程代理
        GenericService genericService = ref.get();
        // 3 执行泛化调用
        Object result = genericService.$invoke(methodName, typeList, valueList);

        return JSON.toJSONString(result);
    }
}
