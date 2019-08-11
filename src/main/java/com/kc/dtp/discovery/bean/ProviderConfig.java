package com.kc.dtp.discovery.bean;

import lombok.Data;

import java.util.List;

/**
 * @Author: Kyle
 * @Description: Dubbo providers
 */
@Data
public class ProviderConfig extends HostConfig {
    private List<String> methods;
}
