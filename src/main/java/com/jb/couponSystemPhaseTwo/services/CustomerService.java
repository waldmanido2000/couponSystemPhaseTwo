package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface CustomerService {
    int customerId = 0;
    boolean login(String email, String password) throws SQLException, CouponSystemException;
    void purchaseCoupon(Coupon coupon) throws SQLException, CouponSystemException;
    List<Coupon> getCustomerCoupons() throws SQLException;
    List<Coupon> getCustomerCoupons(Category category) throws SQLException;
    List<Coupon> getCustomerCoupons(double maxPrice) throws SQLException;
    Customer getCustomerDetails() throws SQLException;
}
