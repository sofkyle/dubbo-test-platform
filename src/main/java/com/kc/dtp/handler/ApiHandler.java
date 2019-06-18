package com.kc.dtp.handler;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author: Kyle
 */
@Component
public class ApiHandler {
    public Mono<Integer> addApi(String url) {
        return Mono.just(1);
    }
}
