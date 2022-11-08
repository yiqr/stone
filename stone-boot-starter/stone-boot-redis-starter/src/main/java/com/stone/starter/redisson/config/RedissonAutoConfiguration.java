package com.stone.starter.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.RedissonRxClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

/**
 * @author rose
 * @date 2022/11/8 13:51
 */
@AutoConfiguration
public class RedissonAutoConfiguration {

    @Bean
    public Config config() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("redisson-config.yaml");
        // InputStream inputStream = resource.getInputStream();
        try {
            return Config.fromYAML(resource.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    @Primary
    public RedissonClient redissonClient(@Qualifier("config") Config config) {
        return Redisson.create(config);
    }

    @Bean
    public RedissonReactiveClient redissonReactiveClient(@Qualifier("redissonClient") RedissonClient redissonClient) {
        return redissonClient.reactive();
    }

    @Bean
    public RedissonRxClient redissonRxClient(@Qualifier("redissonClient") RedissonClient redissonClient) {
        return redissonClient.rxJava();
    }
}
