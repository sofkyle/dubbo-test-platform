package com.kc.dtp.discovery.invoker;

import com.kc.dtp.discovery.bean.ReferenceConfig;

public interface DubboInvoker {
    void config(ReferenceConfig referenceConfig);

    Object doInvoke(String method, String[] parameterTypes, Object[] parameterList);
}
