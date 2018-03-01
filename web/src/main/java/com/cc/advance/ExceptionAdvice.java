package com.cc.advance;

import com.cc.exception.AuthorException;
import com.cc.exception.BusinessException;
import com.cc.pack.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.LinkedList;
import java.util.List;

/**
 * 统一异常捕获返回类
 *
 * @author cyc
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    /**
     * 400 - 参数无法转换为JSON
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("不能转换为JSON", e);
        return new Response().failure("不能转换为JSON");
    }

    /**
     * 400 - 未通过自动参数校验
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数校验未通过", e);
        List<String> verifyFieldsErrors = new LinkedList<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> verifyFieldsErrors.add(fieldError.getDefaultMessage()));
        return new Response().failure("参数校验未通过!"+ verifyFieldsErrors);
    }

    /**
     * 401 - 无授权登录
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public Response handleAccessDeniedException(AccessDeniedException e) {
        log.error("", e);
        return new Response().failure("未登录或登录已过期");
    }

    /**
     * 401 - 无授权登录
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorException.class)
    public Response handleAuthorException(BusinessException e) {
        log.error("未登录或登录已过期", e);
        return new Response().failure("未登录或登录已过期");
    }

    /**
     * 405 - 不支持此方法
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法", e);
        return new Response().failure("不支持当前请求方法");
    }

    /**
     * 415 - 不支持的媒体类型
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Response handleHttpMediaTypeNotSupportedException(Exception e) {
        log.error("不支持当前媒体类型", e);
        return new Response().failure("不支持当前媒体类型");
    }

    /**
     * 500 - 服务器错误（自定义业务异常）
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public Response handleBusinessException(BusinessException e) {
        log.error("不支持当前媒体类型", e);
        return new Response().failure(e.getErrMessage());
    }

    /**
     * 500 - 服务器错误（未知异常）
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        log.error("系统服务异常", e);
        return new Response().failure("系统服务异常");
    }

}