package com.kc.dtp.invoker;

import com.kc.dtp.invoker.impl.*;

public class DubboInvokerFactory {
    public static DubboInvoker get(String version) {
        // 如果存在新的dubbo就使用新的，否则使用旧的
        if (version.compareTo("2.7.0") <= 0) {
            return new OldDubboInvoker();
        } else {
            return new ApacheDubboInvoker();
        }
    }
}
