package com.localpulse.advice;

import com.localpulse.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler
    public ProblemDetail handleException(BadRequestException ex){  //RFC9457
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,ex.getMessage());
    }
}
