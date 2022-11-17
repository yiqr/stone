package com.stone.app.core.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author rose
 * @date 2022-11-17 22:01
 */
@Data
@Component
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {

    /** Request Headers ： Authorization */
    private String header;

    /** Base64对该令牌进行编码 */
    private String signingKey;

    /** 令牌过期时间 此处单位秒 */
    private Long  tokenValidity;
}
