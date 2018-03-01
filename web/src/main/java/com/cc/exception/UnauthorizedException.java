package com.cc.exception;

/**
 * 定义系统的业务异常
 */
public class UnauthorizedException extends RuntimeException{

    private String errMessage;

    public UnauthorizedException(String errMessage){
        this.errMessage=errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

}