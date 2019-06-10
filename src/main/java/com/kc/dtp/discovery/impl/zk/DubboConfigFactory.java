package com.kc.dtp.discovery.impl.zk;

import com.kc.dtp.discovery.bean.DubboConfigHolder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Kyle
 * @Description: Exacting and caching the dubbo config
 */
public class DubboConfigFactory {
    private final Map<String, DubboConfigHolder> dubboConfigHolders = new ConcurrentHashMap(64);

    public void setConfig(String className, DubboConfigHolder configHolder) {
        dubboConfigHolders.put(className, configHolder);
    }

    public DubboConfigHolder getConfig(String className) {
        return dubboConfigHolders.get(className);
    }
}
