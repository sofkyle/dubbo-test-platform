package com.kc.dtp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kc.dtp.bean.vo.ApiVO;
import com.kc.dtp.bean.vo.ParamVO;
import com.kc.dtp.bean.vo.ServiceVO;
import com.kc.dtp.common.ApplicationConfigInstance;
import com.kc.dtp.common.ClassUtils;
import com.kc.dtp.common.InterfaceParser;
import com.kc.dtp.common.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: Kyle
 */
@Slf4j
@Controller
@RequestMapping("/api")
public class ApiController {

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

        List<String> serviceList = ProviderService.get(address).getProviders(protocol, address, registryGroup);

        if (CollectionUtils.isEmpty(serviceList)) {
            model.addAttribute("errMsg", "服务不存在");
            return "404";
        }

        // 按组区分服务
        Map<String, List<String>> serviceGroupList = Maps.newHashMap();
        for (String service : serviceList) {
            int index = service.indexOf("/");
            String groupKey;
            String name;
            if (index >= 0) {
                groupKey = service.substring(0, index);
            } else {
                groupKey = "#";
            }
            name = service;

            List<String> methodList = serviceGroupList.get(groupKey);
            if (CollectionUtils.isEmpty(methodList)) {
                methodList = Lists.newLinkedList();
            }
            methodList.add(name);
            serviceGroupList.put(groupKey, methodList);
        }
        // 将方法列表转为数组，方便前端处理
        Map<String, String> serviceGroupStrList = Maps.newHashMap();
        for (Map.Entry<String, List<String>> entry : serviceGroupList.entrySet()) {
            serviceGroupStrList.put(entry.getKey(), JSON.toJSONString(entry.getValue()));
        }

        model.addAttribute("protocol", protocol);
        model.addAttribute("address", address);
        model.addAttribute("serviceGroupStrList", serviceGroupStrList);
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
        ServiceVO interfaceVO = InterfaceParser.parseUrl(url);

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
        List<ParamVO> paramList;
        String[] typeArray = null;
        Object[] valueArray = null;
        if (!CollectionUtils.isEmpty(paramListStr)) {
            paramList = paramListStr.stream()
                    .map(param -> JSONObject.parseObject(param, new TypeReference<ParamVO>() {}))
                    .collect(Collectors.toList());

            Pair<List<String>, List<Object>> typeValuePair = ClassUtils.parseParameter(paramList);

            typeArray = typeValuePair.getLeft().toArray(new String[typeValuePair.getLeft().size()]);
            valueArray = typeValuePair.getRight().toArray();
        }
        String methodName = queryParams.get("methodName").get(0);
        String serviceGroup = serviceName.substring(0, serviceName.indexOf("/"));
        if (!serviceGroup.isEmpty()) {
            serviceName = serviceName.substring(serviceName.indexOf("/") + 1);
        }

        ReferenceConfig<GenericService> ref = new ReferenceConfig<>();
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
        if (!serviceGroup.isEmpty()) {
            ref.setGroup(serviceGroup);
        }

        // 1.4 泛化标识
        ref.setGeneric(true);

        // 2 获取远程代理
        GenericService genericService = ref.get();
        // 3 执行泛化调用
        Object result = "调用失败";
        try {
            result = genericService.$invoke(methodName, typeArray, valueArray);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return JSON.toJSONString(result);
    }
}
