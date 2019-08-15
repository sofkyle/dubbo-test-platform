package com.kc.dtp.invoker;

import com.kc.dtp.invoker.bean.ReferenceConfig;

public interface DubboInvoker {
    /**
     * 组装Dubbo配置
     * @param referenceConfig
     */
    void config(ReferenceConfig referenceConfig);

    /**
     * 泛化调用
     * @param method
     * @param parameterTypes
     * @param parameterList
     * @return
     */
    Object doInvoke(String method, String[] parameterTypes, Object[] parameterList);
}
