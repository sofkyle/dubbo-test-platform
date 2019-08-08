package com.kc.dtp.discovery;

/**
 * @Author: Kyle
 * @Description: Config discovery interface
 */
public interface Discovery<T> {
    /**
     * Read from root
     * @return
     * @throws Exception
     * @param serviceName
     */
    T readRoot(String serviceName) throws Exception;
}
