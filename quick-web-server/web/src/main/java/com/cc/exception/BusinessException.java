package com.cc.exception;


/**
 * 定义系统的业务异常
 *
 * @author cc
 */
public class BusinessException extends RuntimeException{

    private String errMessage;

    public BusinessException(String errMessage){
        this.errMessage=errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

}