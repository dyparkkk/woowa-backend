package com.example.woowabackend.util;

import com.example.woowabackend.member.exception.DuplicateUserIdException;
import com.example.woowabackend.member.exception.ErrorResponse;
import com.example.woowabackend.member.exception.PwNotMatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({DuplicateUserIdException.class, UsernameNotFoundException.class, PwNotMatchException.class})
    public ErrorResponse illegalExHandler(DuplicateUserIdException e) {
        log.error("[exceptionHandler] ex ", e);
        return ErrorResponse.createErrorResponse(false, "---", "invalid_parameter", e.getMessage());
    }
}