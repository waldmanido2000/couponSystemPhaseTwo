package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.dto.LoginResDto;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface CompanyService {
    LoginResDto login(String email, String password) throws SQLException, CouponSystemException, CouponSecurityException;

    void addCoupon(int companyId, Coupon coupon) throws SQLException, CouponSystemException;

    void updateCoupon(int companyId, int couponId, Coupon coupon) throws SQLException, CouponSystemException;

    void deleteCoupon(int companyId, int couponId) throws SQLException, CouponSystemException;

    List<Coupon> getCompanyCoupons(int companyId) throws SQLException;

    List<Coupon> getCompanyCoupons(int companyId, Category category) throws SQLException;

    List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws SQLException;

    Company getCompanyDetails(int companyId) throws SQLException, CouponSystemException;
}
