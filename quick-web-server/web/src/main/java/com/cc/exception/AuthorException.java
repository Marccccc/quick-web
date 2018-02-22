package com.cc.exception;

/**
 * 定义系统的业务异常
 */
public class AuthorException extends RuntimeException{

    private String errMessage;

    public AuthorException(String errMessage){
        this.errMessage=errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

}