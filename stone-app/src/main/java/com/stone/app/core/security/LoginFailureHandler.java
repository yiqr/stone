package com.stone.app.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stone.commons.jaxrs.GlobalErrorCode;
import com.stone.commons.jaxrs.RespondedBody;
import com.stone.commons.utils.ResponseUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登录失败处理器
 *
 * @author rose
 * @date 2022-11-17 22:53
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ResponseUtils.response(response, MediaType.APPLICATION_JSON, HttpServletResponse.SC_OK,
                objectMapper.writeValueAsString(RespondedBody.of(GlobalErrorCode.LOGIN_FAIL, exception.getMessage())));
    }
}