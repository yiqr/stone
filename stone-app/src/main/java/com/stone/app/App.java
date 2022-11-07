package com.stone.app;

import com.stone.starter.quartz.config.QuartzConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author rose
 * @date 2022/11/7 19:00
 */
@Slf4j
@SpringBootApplication
@Import(QuartzConfiguration.class)
public class App implements CommandLineRunner {

    public static void main(String... args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(App.class);
        Environment env = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application profiles active: '{}' \n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("spring.profiles.active"),
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
