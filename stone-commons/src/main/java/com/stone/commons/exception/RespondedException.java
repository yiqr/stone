package com.stone.commons.exception;

import lombok.Getter;

/**
 * @author rose
 * @Date 2022/10/17 14:03
 */
@Getter
public class RespondedException extends RuntimeException {
    private final int code;
    private String msg;

    public RespondedException(int code) {
        this.code = code;
    }
    public RespondedException(int code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }
    public RespondedException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }
    public RespondedException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.msg = message;
    }
    public static RespondedException of(int errorCode, String errorMsg) {
        return new RespondedException(errorCode, errorMsg);
    }
}
