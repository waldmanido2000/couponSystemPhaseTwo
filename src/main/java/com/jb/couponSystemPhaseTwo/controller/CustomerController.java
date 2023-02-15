package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public interface CustomerController {
    void purchaseCoupon(int customerId, Coupon coupon) throws CouponSystemException, SQLException;

    List<Coupon> getCustomerCoupons(int customerId) throws SQLException;

    List<Coupon> getCustomerCoupons(int customerId, Category category) throws SQLException;

    List<Coupon> getCustomerCoupons(int customerId, double maxPrice) throws SQLException;

    Customer getCustomerDetails(int customerId) throws CouponSystemException, SQLException;
}
