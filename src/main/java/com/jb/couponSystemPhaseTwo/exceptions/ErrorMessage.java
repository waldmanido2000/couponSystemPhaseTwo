package com.jb.couponSystemPhaseTwo.exceptions;

import com.jb.couponSystemPhaseTwo.utils.MessageColor;

public enum ErrorMessage {
    COMPANY_NOT_EXIST("company does not exist"),
    CUSTOMER_NOT_EXIST("customer does not exist"),
    COUPON_NOT_EXIST("coupon does not exist"),
    CANT_CHANGE_COMPANY_NAME("can't change company's name"),
    COMPANY_EXISTS_BY_NAME("there is already a company with the same name"),
    COMPANY_EXISTS_BY_EMAIL("there is already a company with the same email"),
    CUSTOMER_EXISTS_BY_EMAIL("there is already a customer with the same email"),
    COMPANY_COUPON_EXISTS_BY_TITLE("there is already a coupon with the same title for this company"),
    COUPON_ALREADY_PURCHASED("can't purchase coupon more than once"),
    COUPON_OUT_OF_STOCK("coupon is out of stock"),
    COUPON_IS_OUT_DATED("coupon is out-dated");

    private final String Message;
    private final String TEXT_COLOR = MessageColor.ANSI_RED.getTextColor();
    private final String TEXT_RESET = MessageColor.ANSI_RESET.getTextColor()+MessageColor.ANSI_BG_BLACK.getTextColor();

    ErrorMessage(String message) {
        this.Message = String.format("%s\t\t* CouponSystemException: %s *\t\t%s\n", TEXT_COLOR, message, TEXT_RESET);
    }

    public String getMessage() {
        return Message;
    }
}
