package com.jb.couponSystemPhaseTwo.exceptions;

import com.jb.couponSystemPhaseTwo.utils.MessageColor;

public enum ErrorMessage {
    LOGIN_FAIL("login details do not match"),
    COMPANY_NOT_EXIST("company does not exist"),
    CUSTOMER_NOT_EXIST("customer does not exist"),
    COUPON_NOT_EXIST("coupon does not exist"),
    CATEGORY_NOT_EXIST("category does not exist"),
    CANT_CHANGE_COMPANY_ID("can't change company's id"),
    CANT_CHANGE_COMPANY_NAME("can't change company's name"),
    COMPANY_EXISTS_BY_NAME("there is already a company with the same name"),
    COMPANY_EXISTS_BY_EMAIL("there is already a company with the same email"),
    CUSTOMER_EXISTS_BY_EMAIL("there is already a customer with the same email"),
    CANT_CHANGE_CUSTOMER_ID("can't change customer's id"),

    COMPANY_COUPON_EXISTS_BY_TITLE("there is already a coupon with the same title for this company"),
    CANT_CHANGE_COUPON_ID("can't change coupon's id"),
    CANT_CHANGE_COUPONS_COMPANY_ID("can't change coupon's company_id"),

    COUPON_ALREADY_PURCHASED("can't purchase coupon more than once"),
    COUPON_OUT_OF_STOCK("coupon is out of stock"),
    COUPON_IS_OUT_DATED("coupon is out-dated");

    private final String Message;
    private final String TEXT_COLOR = MessageColor.ANSI_RED.getTextColor();
    private final String TEXT_RESET = MessageColor.ANSI_RESET.getTextColor();

    ErrorMessage(String message) {
        this.Message = String.format("%s\t\t* CouponSystemException: %s *\t\t%s", TEXT_COLOR, message, TEXT_RESET);
    }

    public String getMessage() {
        return Message;
    }
}