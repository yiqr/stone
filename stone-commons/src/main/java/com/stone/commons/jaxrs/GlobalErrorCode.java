package com.stone.commons.jaxrs;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author rose
 * @date 2022/10/17 14:19
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalErrorCode {

    //-----------------------------------------全局错误状态---------------------------------------------------
    public static final int SUCCESSFUL = 0;    // 请求成功
    public static final int FAIL = 10000; // 失败
    public static final int SC_UNAUTHORIZED = 401;           // 未授权
    public static final int SC_FORBIDDEN = 403;              // 权限不足
    public static final int TOKEN_EXPIRED = 4001;            // 权限过期
    public static final int API_ARGUMENT_INVALID = 10001;    // API请求参数校验不通过（如: NotBlank NotEmpty）
    public static final int API_VIOLATION_ERROR = 10002;     // 实体或方法参数约束校验不通过
    public static final int API_REQUEST_TIMEOUT = 10003;     // API请求超时
    public static final int SYSTEM_BUSY = -1;                // 系统繁忙，请稍候再试
    public static final int UNDEFINED = 65535;

    public static final int LOGIN_FAIL = 10003;              // 登录失败


}
