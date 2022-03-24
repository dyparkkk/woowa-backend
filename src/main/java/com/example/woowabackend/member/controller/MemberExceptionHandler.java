package com.example.woowabackend.member.controller;

import com.example.woowabackend.member.exception.DuplicateUserIdException;
import com.example.woowabackend.member.exception.ErrorResponse;
import com.example.woowabackend.member.exception.PwNotMatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MemberExceptionHandler {

    @ExceptionHandler({DuplicateUserIdException.class, UsernameNotFoundException.class,PwNotMatchException.class})
    public ErrorResponse illegalExHandler(DuplicateUserIdException e) {
        log.error("[exceptionHandler] ex ", e);
        return new ErrorResponse("invalid_parameter", e.getMessage());
    }

    @ExceptionHandler
    public ErrorResponse exception(Exception e){
        return new ErrorResponse("__", "내부 오류");
    }
}
