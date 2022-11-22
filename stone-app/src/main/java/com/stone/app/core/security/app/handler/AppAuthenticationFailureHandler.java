//package com.stone.app.core.security.app.handler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stone.commons.jaxrs.GlobalErrorCode;
//import com.stone.commons.jaxrs.RespondedBody;
//import com.stone.commons.utils.ResponseUtils;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author rose
// * @date 2022-11-19 12:10
// */
//@Component
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//public class AppAuthenticationFailureHandler implements AuthenticationFailureHandler {
//
//    private final ObjectMapper objectMapper;
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        ResponseUtils.response(response, MediaType.APPLICATION_JSON, HttpServletResponse.SC_OK,
//                objectMapper.writeValueAsString(RespondedBody.of(GlobalErrorCode.LOGIN_FAIL, exception.getMessage())));
//    }
//}
