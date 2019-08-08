package com.kc.dtp.exception;

public class ServiceNotFoundException extends Exception {
    public ServiceNotFoundException(String serviceName) {
        super("未发现服务：" + serviceName);
    }
}
