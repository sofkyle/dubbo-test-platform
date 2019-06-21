package com.kc.dtp.config;

import org.springframework.boot.web.reactive.context.ReactiveWebApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

/**
 * @author: Kyle
 */
@Configuration
public class FreemarkerConfig implements WebFluxConfigurer {
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(ReactiveWebApplicationContext applicationContext) {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("classpath:/templates/");
        configurer.setResourceLoader(applicationContext);
        return configurer;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.freeMarker();
    }
}