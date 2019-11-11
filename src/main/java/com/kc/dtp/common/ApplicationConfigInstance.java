package com.kc.dtp.common;

import org.apache.dubbo.config.ApplicationConfig;

public class ApplicationConfigInstance {
    private static final String name = "dubbo-test-platform";

    private static ApplicationConfig applicationConfig = new ApplicationConfig(name);

    public static ApplicationConfig get() {
        return applicationConfig;
    }
}
