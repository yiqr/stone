package com.stone.app.core.security.jwt;

import com.stone.app.core.security.model.PersistentToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rose
 * @date 2022-11-17 22:07
 */
@Component
public class JwtUtils {
    @Resource
    private JwtProperties jwtProperties;


    /**
     * 生成token
     *
     * @param persistentToken
     * @return
     */
    public PersistentToken generateToken(PersistentToken persistentToken) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", persistentToken.getUsername());
        map.put("ipAddress", persistentToken.getIpAddress());
        long expiration = System.currentTimeMillis() + (jwtProperties.getTokenValidity() * 1000);
        String token = Jwts.builder()
                .setClaims(map)
                .setSubject(persistentToken.getUserId())
                .setExpiration(new Date(expiration))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSigningKey().getBytes(StandardCharsets.UTF_8))
                .compact();
        persistentToken.setToken(token);
        persistentToken.setExpire(expiration);
        return persistentToken;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public TokenClaims analysisToken(String token) {
        Claims body = Jwts.parser()
                .setSigningKey(jwtProperties.getSigningKey().getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
        return TokenClaims.analysis(body);
    }

//    /**
//     * 生成token
//     *
//     * @param name 用户名称
//     * @return
//     */
//    public String generateToken(String name) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("username", name);
//        return Jwts.builder()
//                //设置用户信息
//                .setClaims(map)
//                //token过期时间
//                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getTokenValidity() * 1000))
//                //设置主题
//                .setSubject("Wsl_system")
//                //设置签名
//                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSigningKey().getBytes(StandardCharsets.UTF_8))
//                .compact();
//    }
//
//    /**
//     * 解析token
//     *
//     * @param token token
//     */
//    public String analysisToken(String token) {
//        Claims body = Jwts.parser()
//                .setSigningKey(jwtProperties.getSigningKey().getBytes(StandardCharsets.UTF_8))
//                .parseClaimsJws(token)
//                .getBody();
//
//        return body.get("username").toString();
//    }

}
