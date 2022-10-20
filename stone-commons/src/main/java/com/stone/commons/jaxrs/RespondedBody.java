package com.stone.commons.jaxrs;

import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author rose
 * @date 2022/10/17 14:17
 */
@Getter
public class RespondedBody extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "message";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 RespondedBody 对象，使其表示一个空消息。
     */
    public RespondedBody() {
    }

    /**
     * 初始化一个新创建的 RespondedBody 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public RespondedBody(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 RespondedBody 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public RespondedBody(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (Objects.nonNull(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 方便链式调用
     *
     * @param key map.key
     * @param value map.value
     * @return com.stone.commons.jaxrs.RespondedBody
     */
    @Override
    public RespondedBody put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 返回成功消息
     *
     * @return com.stone.commons.jaxrs.RespondedBody
     */
    public static RespondedBody success() {
        return RespondedBody.success("operation successful");
    }

    /**
     * 返回成功数据
     *
     * @return com.stone.commons.jaxrs.RespondedBody
     */
    public static RespondedBody success(Object data) {
        return RespondedBody.success("operation successful", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return com.stone.commons.jaxrs.RespondedBody
     */
    public static RespondedBody success(String msg) {
        return RespondedBody.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return com.stone.commons.jaxrs.RespondedBody
     */
    public static RespondedBody success(String msg, Object data) {
        return new RespondedBody(GlobalErrorCode.SUCCESSFUL, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return com.stone.commons.jaxrs.RespondedBody
     */
    public static RespondedBody error() {
        return RespondedBody.error("operation failed");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return com.stone.commons.jaxrs.RespondedBody
     */
    public static RespondedBody error(String msg) {
        return RespondedBody.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return com.stone.commons.jaxrs.RespondedBody
     */
    public static RespondedBody error(String msg, Object data) {
        return new RespondedBody(GlobalErrorCode.FAIL, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return com.stone.commons.jaxrs.RespondedBody
     */
    public static RespondedBody error(int code, String msg) {
        return new RespondedBody(code, msg, null);
    }

    /**
     * 返回自定义信息
     *
     * @param code 状态码
     * @param msg  返回内容 message
     * @return com.stone.commons.jaxrs.RespondedBody
     */
    public static RespondedBody of(int code, String msg) {
        return new RespondedBody(code, msg, null);
    }

    /**
     * 返回自定义信息
     *
     * @param code 状态码
     * @param msg  返回内容 message
     * @param data 数据对象
     * @return com.stone.commons.jaxrs.RespondedBody
     */
    public static RespondedBody of(int code, String msg, Object data) {
        return new RespondedBody(code, msg, data);
    }
}

