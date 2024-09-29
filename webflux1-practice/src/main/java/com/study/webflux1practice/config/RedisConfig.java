package com.study.webflux1practice.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ReactiveRedisTemplate;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class RedisConfig implements ApplicationListener<ApplicationReadyEvent> {

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        reactiveRedisTemplate.opsForValue().get("1")
            .doOnSuccess(sucess -> log.info("Initialize to redis conneciton: {}"))
            .doOnError(error -> log.error("Failed to initialize redis connection: {}", error.getMessage()))
            .subscribe();
    }
}
