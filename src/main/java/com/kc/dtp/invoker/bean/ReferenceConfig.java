package com.kc.dtp.invoker.bean;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import lombok.Data;

import java.util.List;

@Data
public class ReferenceConfig {
    ApplicationConfig application;
    List<RegistryConfig> registries;

    private String url;
    private String protocol;
    private Integer timeout;
    private Integer retries;
    private String version;
    private String cluster;
    private String group;
    private Integer connections;
    private String loadBalance;
    private Boolean generic;
    private String serviceInterface;

    public RegistryConfig getRegistry() {
        return this.registries.get(0);
    }
}
