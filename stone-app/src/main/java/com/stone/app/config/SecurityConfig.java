package com.stone.app.config;

import com.stone.app.commons.Constants;
import com.stone.app.core.security.app.AppUserDetailsService;
import com.stone.app.core.security.app.filter.AppAuthTokenFilter;
import com.stone.app.core.security.app.handler.AppAccessDeniedHandler;
import com.stone.app.core.security.app.handler.AppAuthenticationEntryPoint;
import com.stone.app.core.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author rose
 * @date 2022-11-17 23:00
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Configuration
    @Order(1)
    public static class AppSecurityConfig {
        private static final String APP_API_MATCH = "/api/app/**";
        private final AppAuthenticationEntryPoint appAuthenticationEntryPoint;
        private final AppAccessDeniedHandler appAccessDeniedHandler;
        private final PasswordEncoder passwordEncoder;
        private final AppUserDetailsService appUserDetailsService;
        private final JwtUtils jwtUtils;

        @Autowired
        public AppSecurityConfig(AppAuthenticationEntryPoint appAuthenticationEntryPoint
                , AppAccessDeniedHandler appAccessDeniedHandler
                , @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder
                , AppUserDetailsService appUserDetailsService
                , JwtUtils jwtUtils) {
            this.appAuthenticationEntryPoint = appAuthenticationEntryPoint;
            this.appAccessDeniedHandler = appAccessDeniedHandler;
            this.passwordEncoder = passwordEncoder;
            this.appUserDetailsService = appUserDetailsService;
            this.jwtUtils = jwtUtils;
        }

        @Bean
        public AuthenticationManager appAuthenticationManager() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setPasswordEncoder(passwordEncoder);
            provider.setUserDetailsService(appUserDetailsService);
            return new ProviderManager(provider);
        }

        @Bean
        public AppAuthTokenFilter appAuthTokenFilter() {
            return new AppAuthTokenFilter(jwtUtils, appUserDetailsService, new AntPathRequestMatcher(APP_API_MATCH));
        }

        @Bean
        public SecurityFilterChain appFilterChain(HttpSecurity http) throws Exception {
            http.cors()
                    .and()
                    .csrf().disable()
                    // 自定义认证失败类
                    .exceptionHandling().authenticationEntryPoint(appAuthenticationEntryPoint)
                    // 自定义权限不足处理类
                    .accessDeniedHandler(appAccessDeniedHandler)
                    .and()
                    //设置无状态登录
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authenticationManager(appAuthenticationManager())
                    .antMatcher(APP_API_MATCH)
                    .authorizeHttpRequests()
                    .antMatchers("/api/app/search/**"
                            , "/api/app/demo/**"
                            , "/api/auth/**"
                            , "/api/app/test/**").permitAll()
                    .anyRequest().hasAnyAuthority(Constants.ROLE_USER);
//                    .anyRequest().authenticated();

            http.addFilterBefore(appAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }
    }
}
