package com.zhtty.mock.box.exception;

/**
 * @author A8142
 */
public enum ExceptionMessageEnum {

    ERROR(6000, "系统异常"),
    ID_IS_NULL(6001, "ID IS NULL"),
    USER_NO_OR_PASSWORD_IS_NULL(6002, "用户名或密码为空"),
    USER_NO_OR_PASSWORD_IS_ERROR(6003, "用户名或密码为错误"),
    USER_IS_NOT_EXIST(6004, "用户不存在"),
    ENCRYPTION_ERROR(7000, "加密异常"),
    AUTHENTICATION_FAIL(7001, "认证失败"),
    ROUTES_LOAD_FAIL(8001,"路由加载失败");


    public Integer code;

    public String message;

    ExceptionMessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
