package com.stone.app.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author rose
 * @date 2022/11/8 14:52
 */
@Data
@ConfigurationProperties("spring.datasource")
public class HikariConfigProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private HikariBean hikari;

    @Data
    public static class HikariBean {
        private String poolName;
        private Long connectionTimeout;
        private Integer minimumIdle;
        private Integer maximumPoolSize;
    }

}
