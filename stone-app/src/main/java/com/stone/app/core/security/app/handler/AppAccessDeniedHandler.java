package com.stone.app.core.security.app.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stone.commons.jaxrs.GlobalErrorCode;
import com.stone.commons.jaxrs.RespondedBody;
import com.stone.commons.utils.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 权限不足处理器
 *
 * @author rose
 * @date 2022-11-17 22:24
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtils.response(response, MediaType.APPLICATION_JSON, HttpServletResponse.SC_OK,
                objectMapper.writeValueAsString(RespondedBody.of(GlobalErrorCode.SC_FORBIDDEN, accessDeniedException.getMessage())));
    }
}
