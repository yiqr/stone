package com.stone.app.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stone.app.core.security.jwt.JwtUtils;
import com.stone.commons.jaxrs.RespondedBody;
import com.stone.commons.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 认证成功处理器
 *
 * @author rose
 * @date 2022-11-17 22:35
 */
@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    private JwtUtils jwtUtils;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String name = authentication.getName();
        //生成token
        String token = jwtUtils.generateToken(name);
        //将生成的authentication放入容器中，生成安全的上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ResponseUtils.response(httpServletResponse, MediaType.APPLICATION_JSON, HttpServletResponse.SC_OK,
                objectMapper.writeValueAsString(RespondedBody.success(token)));
    }
}
