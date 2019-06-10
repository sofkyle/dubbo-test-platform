package com.kc.dtp.discovery;

import java.util.List;

/**
 * @Author: Kyle
 * @Description: Config discovery interface
 */
public interface Discovery<T> {
    List<T> readAll() throws Exception;
}
