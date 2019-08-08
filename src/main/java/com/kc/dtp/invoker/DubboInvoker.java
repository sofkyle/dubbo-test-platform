package com.kc.dtp.invoker;

import com.kc.dtp.invoker.bean.ReferenceConfig;

public interface DubboInvoker {
    void config(ReferenceConfig referenceConfig);

    Object doInvoke(String method, String[] parameterTypes, Object[] parameterList);
}
