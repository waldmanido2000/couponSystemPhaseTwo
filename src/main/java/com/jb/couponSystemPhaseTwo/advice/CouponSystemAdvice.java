package com.jb.couponSystemPhaseTwo.advice;

import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponSystemAdvice {

    @ExceptionHandler(value = {CouponSystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleCouponSystemException(CouponSystemException e) {
        return new ErrorDetails(e.getMessage());
    }

    //    @ExceptionHandler(value = {CouponSecurityException.class})
//    public ResponseEntity<?> handleCouponSecurityException(CouponSecurityException e) {
//        ErrorDetails error = ErrorDetails.builder().value(e.getMessage()).build();
//        HttpStatus status = e.getStatus();
//        return new ResponseEntity<>(error, status);
//    }
    @ExceptionHandler(value = {CouponSecurityException.class})
    public ResponseEntity<ErrorDetails> handleCouponSecurityException(CouponSecurityException e) {
        ErrorDetails error = ErrorDetails.builder().value(e.getMessage()).build();
        HttpStatus status = e.getStatus();
        return new ResponseEntity<>(error, status);
    }

}