package com.drf.wy.exception;



import com.drf.wy.utils.Result;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;




/**
 * @Auther：杜瑞丰
 * @Date：2023/7/21 15:17
 * @Project_Name:wuyeSys-parent
 */
@RestControllerAdvice("com.drf.wy")
@Slf4j
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public Object handlerException(Exception e){
        log.error("系统异常_"+e.getMessage());
        return Result.error(500,e.getMessage());
    }
}
