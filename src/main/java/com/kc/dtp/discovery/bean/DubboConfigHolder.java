package com.kc.dtp.discovery.bean;

import lombok.Data;

import java.util.List;

/**
 * @Author: Kyle
 * @Description: ZK dubbo config DTO
 */
@Data
public class DubboConfigHolder {
    /**
     * Service Name
     */
    private String serviceName;

    /**
     * Consumers
     */
    private List<ConsumerConfig> consumers;

    /**
     * Providers
     */
    private List<ProviderConfig> providers;
}
