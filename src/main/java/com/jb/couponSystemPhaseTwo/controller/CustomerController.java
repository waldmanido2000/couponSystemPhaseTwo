package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public interface CustomerController {
    void purchaseCoupon(int customerId, Coupon coupon, UUID token) throws CouponSystemException, SQLException, CouponSecurityException;

    List<Coupon> getCustomerCoupons(int customerId, UUID token) throws SQLException, CouponSecurityException;

    List<Coupon> getCustomerCoupons(int customerId, Category category, UUID token) throws SQLException, CouponSecurityException;

    List<Coupon> getCustomerCoupons(int customerId, double maxPrice, UUID token) throws SQLException, CouponSecurityException;

    Customer getCustomerDetails(int customerId, UUID token) throws CouponSystemException, SQLException, CouponSecurityException;
}
