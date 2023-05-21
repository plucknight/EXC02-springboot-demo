package com.sx.exception;

import com.sx.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHander {
    @ExceptionHandler(Exception.class)//捕获所有的异常
    public Result ex(Exception ex){
        ex.printStackTrace();

        return Result.error("操作失败");
    }

}
