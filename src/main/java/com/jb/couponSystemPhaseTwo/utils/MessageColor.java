package com.jb.couponSystemPhaseTwo.utils;

public enum MessageColor {
    ANSI_RESET("\u001B[0m"),
    /* text color */
    ANSI_BLACK("\u001B[30m"),
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_CYAN("\u001B[36m"),
    ANSI_WHITE("\u001B[37m"),

    /* background colors */

    ANSI_BG_BLACK("\u001B[40m"),
    ANSI_BG_RED("\u001B[41m"),
    ANSI_BG_GREEN("\u001B[42m"),
    ANSI_BG_YELLOW("\u001B[43m"),
    ANSI_BG_BLUE("\u001B[44m"),
    ANSI_BG_PURPLE("\u001B[45m"),
    ANSI_BG_CYAN("\u001B[46m"),
    ANSI_BG_WHITE("\u001B[47m");
    private final String textColor;

    MessageColor(String textColor) {
        this.textColor = textColor;
    }

    public String getTextColor() {
        return textColor;
    }
}
