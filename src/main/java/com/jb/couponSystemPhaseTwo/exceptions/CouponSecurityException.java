package com.jb.couponSystemPhaseTwo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CouponSecurityException extends Exception {
    private HttpStatus status;

    public CouponSecurityException(SecurityMessage securityMessage) {
        super(securityMessage.getMessage());
        this.status = securityMessage.getStatus();
    }
}