package com.kc.dtp.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.DispatcherHandler;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.server.WebHandler;

/**
 * @author: Kyle
 */
@Configuration
@ComponentScan
@EnableWebFlux
public class WebFluxConfig {
    @Bean
    public WebHandler webHandler(ApplicationContext applicationContext) {
        DispatcherHandler dispatcherHandler = new DispatcherHandler(applicationContext);
        return dispatcherHandler;
    }
}
