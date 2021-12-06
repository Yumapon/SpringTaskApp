package com.yuma.javaarchitect.springtaskappspring.presentation.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * errorハンドリングクラス
 * 
 */
@ControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @org.springframework.web.bind.annotation.ExceptionHandler({ UnauthorizedError.class })
    @ResponseBody
    public Map<String, Object> handleError() {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("message", "実行が許可されていません");
        errorMap.put("status", HttpStatus.UNAUTHORIZED);
        return errorMap;
    }
    
}