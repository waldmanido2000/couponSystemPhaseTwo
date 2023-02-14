package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface CustomerService {
    boolean login(String email, String password) throws SQLException, CouponSystemException, CouponSecurityException;

    void purchaseCoupon(int customerId, Coupon coupon) throws SQLException, CouponSystemException;

    List<Coupon> getAvailableCoupons();

    List<Coupon> getCustomerCoupons(int customerId) throws SQLException;

    List<Coupon> getCustomerCoupons(int customerId, Category category) throws SQLException;

    List<Coupon> getCustomerCoupons(int customerId, double maxPrice) throws SQLException;

    Customer getCustomerDetails(int customerId) throws SQLException, CouponSystemException;
}
