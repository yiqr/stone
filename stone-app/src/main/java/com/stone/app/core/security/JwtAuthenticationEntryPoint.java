package com.stone.app.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stone.app.core.security.exception.TokenExpiredException;
import com.stone.commons.jaxrs.GlobalErrorCode;
import com.stone.commons.jaxrs.RespondedBody;
import com.stone.commons.utils.ResponseUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 认证失败处理器
 *
 * @author rose
 * @date 2022-11-17 22:31
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (authException instanceof TokenExpiredException) {
            ResponseUtils.response(response, MediaType.APPLICATION_JSON, HttpServletResponse.SC_OK,
                    objectMapper.writeValueAsString(RespondedBody.of(GlobalErrorCode.TOKEN_EXPIRED, authException.getMessage())));
        } else {
            ResponseUtils.response(response, MediaType.APPLICATION_JSON, HttpServletResponse.SC_OK,
                    objectMapper.writeValueAsString(RespondedBody.of(GlobalErrorCode.SC_UNAUTHORIZED, authException.getMessage())));
        }
    }
}

