package com.kc.dtp.service;

import com.kc.dtp.discovery.bean.ProviderConfig;
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
     *
     * @param serviceName
     * @return
     * @throws Exception
     */
    Mono<List<ProviderConfig>> getProviderByServiceName(String serviceName) throws Exception;
}
