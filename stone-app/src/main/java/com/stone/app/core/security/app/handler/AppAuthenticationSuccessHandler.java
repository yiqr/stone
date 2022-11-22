//package com.stone.app.core.security.app.handler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stone.app.core.security.jwt.JwtUtils;
//import com.stone.commons.jaxrs.RespondedBody;
//import com.stone.commons.utils.ResponseUtils;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 认证成功处理器
// *
// * @author rose
// * @date 2022-11-19 10:42
// */
//@Component
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//public class AppAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//    private final JwtUtils jwtUtils;
//    private final ObjectMapper objectMapper;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        String name = authentication.getName();
//        //生成token
//        String token = jwtUtils.generateToken(name);
//        //将生成的authentication放入容器中，生成安全的上下文
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        ResponseUtils.response(response, MediaType.APPLICATION_JSON, HttpServletResponse.SC_OK,
//                objectMapper.writeValueAsString(RespondedBody.success(token)));
//    }
//}
