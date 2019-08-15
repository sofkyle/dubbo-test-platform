package com.kc.dtp.service;

import com.kc.dtp.discovery.bean.ProviderConfig;
import com.kc.dtp.invoker.bean.InvokerParamDTO;
import com.kc.dtp.invoker.bean.ReferenceConfig;
import com.kc.dtp.model.UserApi;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author: Kyle
 */
public interface ApiService {
    /**
     * 获取用户绑定的所有Api
     * @param userId
     * @return
     * @throws Exception
     */
    Flux<UserApi> getAllByUserId(Long userId) throws Exception;

    /**
     * 获取服务提供者
     * @param serviceName
     * @return
     * @throws Exception
     */
    Mono<List<ProviderConfig>> getProviderByServiceName(String serviceName) throws Exception;

    /**
     * 调用服务
     * @param referenceConfig
     * @param invokerParamDTO
     * @return
     * @throws Exception
     */
    Mono<String> invokeService(ReferenceConfig referenceConfig, InvokerParamDTO invokerParamDTO) throws Exception;
}
