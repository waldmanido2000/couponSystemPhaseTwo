package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public interface CompanyService {
    boolean login(String email, String password) throws SQLException, CouponSystemException;

    void addCoupon(Coupon coupon) throws SQLException, CouponSystemException;

    void updateCoupon(int couponId, Coupon coupon) throws SQLException, CouponSystemException;

    void deleteCoupon(int couponId) throws SQLException, CouponSystemException;

    List<Coupon> getCompanyCoupons() throws SQLException;

    List<Coupon> getCompanyCoupons(Category category) throws SQLException;

    ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws SQLException;

    Company getCompanyDetails() throws SQLException;
}
