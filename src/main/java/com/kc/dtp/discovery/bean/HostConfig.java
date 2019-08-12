package com.kc.dtp.discovery.bean;

import lombok.Data;

/**
 * @Author: Kyle
 * @Description: Host type config
 */
@Data
public class HostConfig extends RootConfig {
    /**
     * Host
     */
    protected String host;

    /**
     * Port
     */
    protected int port;
}
