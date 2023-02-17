package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public interface CompanyController {
    void addCoupon(int companyId, Coupon coupon, UUID token) throws CouponSystemException, SQLException, CouponSecurityException;

    void updateCoupon(int companyId, int couponId, Coupon coupon, UUID token) throws CouponSystemException, SQLException, CouponSecurityException;

    void deleteCoupon(int companyId, int couponId, UUID token) throws CouponSystemException, SQLException, CouponSecurityException;

    List<Coupon> getCompanyCoupons(int companyId, UUID token) throws SQLException, CouponSecurityException;

    List<Coupon> getCompanyCoupons(int companyId, Category category, UUID token) throws SQLException, CouponSecurityException;

    List<Coupon> getCompanyCoupons(int companyId, double maxPrice, UUID token) throws SQLException, CouponSecurityException;

    Company getCompanyDetails(int companyId, UUID token) throws CouponSystemException, SQLException, CouponSecurityException;
}
