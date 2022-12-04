package com.jb.couponSystemPhaseTwo.advice;


import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponSystemAdvice {
    @ExceptionHandler(value = {CouponSystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handle(Exception e){
        return new ErrorDetails(e.getMessage());
    }

}
