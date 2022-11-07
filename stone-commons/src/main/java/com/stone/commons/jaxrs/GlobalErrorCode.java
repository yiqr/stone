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
    public static final int SUCCESSFUL = 0,    // 请求成功
            FAIL = 10000, // 失败
            SC_FORBIDDEN = 403,              // 权限不足
            API_ARGUMENT_INVALID = 10001,    // API请求参数校验不通过（如: NotBlank NotEmpty）
            API_VIOLATION_ERROR = 10002,     // 实体或方法参数约束校验不通过
            API_REQUEST_TIMEOUT = 10003,     // API请求超时
            SYSTEM_BUSY = -1,   // 系统繁忙，请稍候再试
            UNDEFINED = 65535;
}
