package com.stone.app.core.security.exception;


import org.springframework.security.core.AuthenticationException;


/**
 * @author rose
 * @date 2022-11-17 22:42
 */
public class TokenExpiredException extends AuthenticationException {

    public TokenExpiredException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
