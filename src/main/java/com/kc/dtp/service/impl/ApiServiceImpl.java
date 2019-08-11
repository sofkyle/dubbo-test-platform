package com.kc.dtp.service.impl;

import com.kc.dtp.discovery.Discovery;
import com.kc.dtp.discovery.bean.DubboConfigHolder;
import com.kc.dtp.discovery.bean.ProviderConfig;
import com.kc.dtp.discovery.bean.ZKDataHolder;
import com.kc.dtp.discovery.impl.zk.ZooKeeperReader;
import com.kc.dtp.model.UserApi;
import com.kc.dtp.repository.UserApiDAO;
import com.kc.dtp.service.ApiService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service("apiService")
public class ApiServiceImpl implements ApiService {

    @Resource
    private Discovery<ZKDataHolder> discovery;

    @Resource
    private UserApiDAO userApiDAO;

    @Override
    public Flux<UserApi> getAllByUserId(Long userId) throws Exception {
        return Flux.just(userApiDAO.selectByUserId(userId));
    }

    @Override
    public Mono<List<ProviderConfig>> getProviderByServiceName(String serviceName) throws Exception {
        discovery.readRoot(serviceName);
        DubboConfigHolder dubboConfigHolder = ZooKeeperReader.dubboConfigHolderMap.get(serviceName);
        if (Objects.isNull(dubboConfigHolder)) {
            return Mono.just(null);
        }
        List<ProviderConfig> providerConfig = dubboConfigHolder.getProviders();

        return Mono.justOrEmpty(providerConfig);
    }
}
