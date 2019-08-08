package com.kc.dtp.invoker;

import com.kc.dtp.invoker.impl.*;

public class DubboInvokerFactory {
    public static DubboInvoker get() {
        // 如果存在新的dubbo就使用新的，否则使用旧的
        try {
            Class.forName("org.apache.dubbo.config.ReferenceConfig");
            return new ApacheDubboInvoker();
        } catch (ClassNotFoundException ignore) {
            return new OldDubboInvoker();
        }
    }
}
