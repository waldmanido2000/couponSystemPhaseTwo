package com.jb.couponSystemPhaseTwo.exceptions;

public class CouponSecurityException extends Exception{
    public CouponSecurityException(SecurityMessage securityMessage) {
        super(securityMessage.getMessage());
    }
}