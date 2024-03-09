package com.macro.mall.tiny.config;

import com.macro.mall.tiny.common.config.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Redis配置类
 * @apiNote 如果要开启redis链接的话可以将开启以下注解
 */
//@EnableCaching
//@Configuration
public class RedisConfig extends BaseRedisConfig {

}
