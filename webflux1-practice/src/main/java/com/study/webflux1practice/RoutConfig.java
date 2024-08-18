package com.study.webflux1practice;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@RequiredArgsConstructor
@Configuration
public class RoutConfig {

    private final SampleHandler sampleHandler;

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions.route()
            .GET("/hello-function", sampleHandler::getString)
            .build();
    }
}
