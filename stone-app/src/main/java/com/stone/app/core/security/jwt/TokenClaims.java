package com.stone.app.core.security.jwt;

import io.jsonwebtoken.Claims;
import lombok.Data;

/**
 * @author rose
 * @date 2022-11-19 17:53
 */
@Data
public class TokenClaims {

    private String userName;

    private String ipAddress;

    public static TokenClaims analysis(Claims claims) {
        TokenClaims tokenClaims = new TokenClaims();
        tokenClaims.setIpAddress(claims.get("ipAddress").toString());
        tokenClaims.setUserName(claims.get("username").toString());
        return tokenClaims;
    }

}
