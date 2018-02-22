package com.cc.pack;

import lombok.Data;

/**
 * 请求相应包装结果
 *
 * @author cc
 */
@Data
public class Response {

    /**
     * 自定义的状态码
     */
    private int code;
    private String message;
    private Object data;

    public Response success() {
        this.code = CustomCode.SUCCESS.getCode();
        return this;
    }

    public Response success(Object data) {
        this.code = CustomCode.SUCCESS.getCode();
        this.data = data;
        return this;
    }

    public Response failure(String message) {
        this.message = message;
        return this;
    }

    public Response failure(CustomCode code) {
        this.code = code.getCode();
        return this;
    }

    public Response failure(CustomCode code, String message) {
        this.code = code.getCode();
        this.message = message;
        return this;
    }

    public Response failure(CustomCode code, String message, Object data) {
        this.code = code.getCode();
        this.message = message;
        this.data = data;
        return this;
    }
}




