package com.jb.couponSystemPhaseTwo.exceptions;

public class CouponSystemException extends Exception {
    public CouponSystemException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
