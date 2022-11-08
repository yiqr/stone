package com.stone.app.config;

import com.stone.app.config.properties.HikariConfigProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author rose
 * @date 2022/11/8 14:51
 */
@Configuration
@ConditionalOnClass({DataSource.class})
@EnableConfigurationProperties(HikariConfigProperties.class)
public class DataSourceConfigure {
    private HikariConfigProperties properties;

    public DataSourceConfigure(HikariConfigProperties properties) {
        this.properties = properties;
    }

    @Bean("hikariConfig")
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getUrl());
        config.setUsername(properties.getUsername());
        config.setPassword(properties.getPassword());
        config.setPoolName(properties.getHikari().getPoolName());
        config.setConnectionTimeout(properties.getHikari().getConnectionTimeout());
        config.setMinimumIdle(properties.getHikari().getMinimumIdle());
        config.setMaximumPoolSize(properties.getHikari().getMaximumPoolSize());
        return config;
    }

    @Bean
    @Primary
    public DataSource dataSource(@Qualifier("hikariConfig") HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }
}
