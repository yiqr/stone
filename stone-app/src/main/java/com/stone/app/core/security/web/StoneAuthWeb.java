package com.stone.app.core.security.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stone.app.core.security.jwt.JwtUtils;
import com.stone.app.core.security.model.PersistentToken;
import com.stone.commons.jaxrs.RespondedBody;
import com.stone.commons.utils.IPUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rose
 * @date 2022-11-19 14:48
 */
@Api("认证管理")
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class StoneAuthWeb {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    private final ObjectMapper objectMapper;

    @Autowired
    public StoneAuthWeb(AuthenticationManager authenticationManager,
                        ObjectMapper objectMapper,
                        JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public RespondedBody loginByPwd(HttpServletRequest request, @RequestParam("username") String loginName, @RequestParam("password") String pwd) {
        // 认证
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginName, pwd));
        // 设置token
        PersistentToken persistentToken = new PersistentToken();
        persistentToken.setUsername(loginName);
        persistentToken.setIpAddress(IPUtils.getIpAddr(request));
        persistentToken = jwtUtils.generateToken(persistentToken);
        // 保存token
        

        return RespondedBody.success(persistentToken);
    }

}
