package com.example.woowabackend.member.controller;

import com.example.woowabackend.member.exception.DuplicateUserIdException;
import com.example.woowabackend.member.exception.ErrorResponse;
import com.example.woowabackend.member.exception.PwNotMatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MemberExceptionHandler {

    @ExceptionHandler({DuplicateUserIdException.class, UsernameNotFoundException.class,PwNotMatchException.class})
    public ErrorResponse illegalExHandler(DuplicateUserIdException e) {
        log.error("[exceptionHandler] ex ", e);
        return ErrorResponse.createErrorResponse(false, "---", "invalid_parameter", e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorResponse typeException(Exception e){
        log.info("[MissingServletRequestParameterException] ex ={}", e.getMessage());
        return ErrorResponse.createErrorResponse(false, "--"+HttpStatus.BAD_REQUEST,
                "[MissingServletRequestParameterException]:파라미터의 문제가 있습니다. 타입이 맞지 않거나 생략되었을 수 있습니다. ",
                e.getMessage());
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public ErrorResponse reqException(Exception e) {
        log.info("[ServletRequestBindingException] ex = {}", e.getMessage());
        return ErrorResponse.createErrorResponse(false, "--" + HttpStatus.BAD_REQUEST,
                "[ServletRequestBindingException]:바인딩 에러 ex) JSESSIONID 확인 불가 등",
                e.getMessage());
    }

    @ExceptionHandler
    public ErrorResponse exception(Exception e){
        return ErrorResponse.createErrorResponse(false, "--"+"???","정의되지 않은 오류( 추가 예정)", e.getMessage());
    }
}
