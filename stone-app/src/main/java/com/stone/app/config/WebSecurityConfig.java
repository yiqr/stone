package com.stone.app.config;

import com.stone.app.core.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author rose
 * @date 2022-11-17 23:00
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;


    @Bean
    public JwtAuthenticationTokenFilter getJwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(jwtAuthenticationSuccessHandler)
                .failureHandler(loginFailureHandler)
                .loginProcessingUrl("/login")
                //关闭csrf
                .and()
                .csrf().disable()
                // 自定义认证失败类
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                // 自定义权限不足处理类
                .accessDeniedHandler(jwtAccessDeniedHandler).and()
                //设置无状态登录
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //过滤登录退出请求
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/login/**").permitAll()
                //所有请求都需要拦截
                .anyRequest()
                .authenticated();
        //添加token验证过期器，并指定过滤器的类型是UsernamePasswordAuthenticationFilter
        http.addFilterBefore(getJwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}