package com.stone.app.config;

import com.stone.commons.core.config.AbstractWebMvcConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * @author rose
 * @date 2022/11/8 15:38
 */
@Slf4j
@Configuration
public class WebMvcConfiguration extends AbstractWebMvcConfiguration {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(super.localeChangeInterceptor());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "OPTIONS", "PUT", "DELETE")
                .allowedHeaders("x-requested-with", "authorization", "Content-Type", "Authorization", "X-Auth-Token", "x-auth-token");
    }
}
