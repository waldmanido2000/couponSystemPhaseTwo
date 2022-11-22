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

    void addCoupon(int companyId, Coupon coupon) throws SQLException, CouponSystemException;

    void updateCoupon(int companyId, int couponId, Coupon coupon) throws SQLException, CouponSystemException;

    void deleteCoupon(int companyId, int couponId) throws SQLException, CouponSystemException;

    List<Coupon> getCompanyCoupons(int companyId) throws SQLException;

    List<Coupon> getCompanyCoupons(int companyId, Category category) throws SQLException;

    ArrayList<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws SQLException;

    Company getCompanyDetails(int companyId) throws SQLException;
}
