package com.stone.app.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stone.commons.jaxrs.GlobalErrorCode;
import com.stone.commons.jaxrs.RespondedBody;
import com.stone.commons.utils.ResponseUtils;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不组处理器
 *
 * @author rose
 * @date 2022-11-17 22:24
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtils.response(response, MediaType.APPLICATION_JSON, HttpServletResponse.SC_OK,
                objectMapper.writeValueAsString(RespondedBody.of(GlobalErrorCode.SC_FORBIDDEN, accessDeniedException.getMessage())));
    }
}
