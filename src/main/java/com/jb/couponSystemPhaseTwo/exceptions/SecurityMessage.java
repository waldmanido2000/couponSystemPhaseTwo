package com.jb.couponSystemPhaseTwo.exceptions;

import com.jb.couponSystemPhaseTwo.utils.MessageColor;
import org.springframework.http.HttpStatus;

public enum SecurityMessage {
    LOGIN_FAIL("login details do not match", HttpStatus.UNAUTHORIZED);

    private final String Message;
    private final HttpStatus Status;
    private final String TEXT_COLOR = MessageColor.ANSI_RED.getTextColor();
    private final String TEXT_RESET = MessageColor.ANSI_RESET.getTextColor()+MessageColor.ANSI_BG_BLACK.getTextColor();

    SecurityMessage(String message, HttpStatus Status) {
        this.Message = String.format("%s\t\t* CouponSystemException: %s *\t\t%s\n", TEXT_COLOR, message, TEXT_RESET);
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }
}
