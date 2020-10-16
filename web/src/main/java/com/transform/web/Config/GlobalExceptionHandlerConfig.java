package com.transform.web.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 参数校验工具-全局异常捕获处理类
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerConfig {
    /**
     * 方法参数校验
     */
    @ExceptionHandler(BindException.class)
    public String handleMethodArgumentNotValidException(BindException e) {
        log.error(e.getMessage(), e);
        System.out.println(e.getBindingResult().getFieldError().getDefaultMessage().length());
        return e.getBindingResult().getFieldError().getDefaultMessage();
    }
}
