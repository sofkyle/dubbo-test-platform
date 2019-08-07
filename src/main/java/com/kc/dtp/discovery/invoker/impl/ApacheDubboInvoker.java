package com.kc.dtp.discovery.invoker.impl;

import com.alibaba.fastjson.JSON;
import com.kc.dtp.discovery.bean.ReferenceConfig;
import com.kc.dtp.discovery.invoker.DubboInvoker;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

public class ApacheDubboInvoker implements DubboInvoker {
    private org.apache.dubbo.config.ReferenceConfig<GenericService> reference = new org.apache.dubbo.config.ReferenceConfig<>();
    private GenericService genericService;

    @Override
    public void config(ReferenceConfig referenceConfig) {
        String referenceJSON = JSON.toJSONString(referenceConfig);
        this.reference = (org.apache.dubbo.config.ReferenceConfig<GenericService>) JSON.parse(referenceJSON);

        ReferenceConfigCache cache = ReferenceConfigCache.getCache(referenceConfig.getRegistry().getAddress());
        genericService = cache.get(reference);
    }

    @Override
    public Object doInvoke(String method, String[] parameterTypes, Object[] parameterList) {
        return null;
    }
}
