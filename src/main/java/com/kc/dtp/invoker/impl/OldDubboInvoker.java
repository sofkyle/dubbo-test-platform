package com.kc.dtp.invoker.impl;

import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import com.kc.dtp.invoker.bean.ReferenceConfig;
import com.kc.dtp.invoker.DubboInvoker;

public class OldDubboInvoker implements DubboInvoker {
    private com.alibaba.dubbo.config.ReferenceConfig<GenericService> reference = new com.alibaba.dubbo.config.ReferenceConfig<>();
    private GenericService genericService;

    @Override
    public void config(ReferenceConfig referenceConfig) {
        String referenceJSON = JSON.toJSONString(referenceConfig);
        this.reference = (com.alibaba.dubbo.config.ReferenceConfig<GenericService>) JSON.parse(referenceJSON);

        ReferenceConfigCache cache = ReferenceConfigCache.getCache(referenceConfig.getRegistry().getAddress());
        genericService = cache.get(reference);
    }

    @Override
    public Object doInvoke(String method, String[] parameterTypes, Object[] parameterList) {
        return genericService.$invoke(method, parameterTypes, parameterList);
    }
}
