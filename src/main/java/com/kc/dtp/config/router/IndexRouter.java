package com.kc.dtp.config.router;

import com.kc.dtp.handler.ApiHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author: Kyle
 */
public class IndexRouter {

    /*@Bean
    public RouterFunction<ServerResponse> routeIndex(ApiHandler apiHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                apiHandler::addApi);
    }*/
}
