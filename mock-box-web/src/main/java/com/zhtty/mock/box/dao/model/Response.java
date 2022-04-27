package com.zhtty.mock.box.dao.model;


import lombok.Data;

import java.io.Serializable;

/**
 * @param <T>
 * @author A8142
 */
@Data
public class Response<T> implements Serializable {

    private static final Integer SUCCESS_CODE = 2000;

    private static final Integer SYSTEM_ERROR_CODE = 6000;

    private Integer code = SUCCESS_CODE;

    private String message;

    private boolean success;

    private T data;

    private String requestId;

    public static <T> Response<T> ofSuccess(T t) {
        Response<T> response = new Response();
        response.setSuccess(true);
        response.setData(t);
        return response;
    }

    public static <T> Response<T> ofFail(String message) {
        return ofFail(message, null);
    }

    public static <T> Response<T> ofFail(String message, Integer code) {
        Response<T> response = new Response();
        if (code == null) {
            response.setCode(SYSTEM_ERROR_CODE);
        } else {
            response.setCode(code);
        }
        response.setMessage(message);
        return response;
    }
}
