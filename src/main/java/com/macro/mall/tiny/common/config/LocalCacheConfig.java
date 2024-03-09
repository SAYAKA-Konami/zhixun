package com.macro.mall.tiny.common.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class LocalCacheConfig {
    @Bean
    public Cache<String, Object> tokenCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(7, TimeUnit.DAYS)  // 设置过期时间为7天
                .initialCapacity(100)  // 设置初始容量
                .maximumSize(500)  // 设置最大容量
                .build();
    }

}
