package com.jb.couponSystemPhaseTwo.clr.on;

import com.jb.couponSystemPhaseTwo.utils.MessageColor;

public enum AnticipatedResult {
    RESET(MessageColor.ANSI_RESET.getTextColor()),
    FAIL(MessageColor.ANSI_RED.getTextColor()),
    SUCCESS(MessageColor.ANSI_GREEN.getTextColor()),
    CONTROL(MessageColor.ANSI_YELLOW.getTextColor());
    private final String Message;

    AnticipatedResult(String message) {
        Message = message + MessageColor.ANSI_BG_BLACK.getTextColor();
    }

    public String getMessage() {
        return Message;
    }
}