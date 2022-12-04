package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public interface CompanyController {
    void addCoupon(int companyId, Coupon coupon) throws CouponSystemException, SQLException;

    void updateCoupon(int companyId, int couponId, Coupon coupon) throws CouponSystemException, SQLException;

    void deleteCoupon(int companyId, int couponId) throws CouponSystemException, SQLException;

    List<Coupon> getCompanyCoupons(int companyId) throws SQLException;

    List<Coupon> getCompanyCoupons(int companyId, Category category) throws SQLException;

    List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws SQLException;

    Company getCompanyDetails(int companyId) throws CouponSystemException, SQLException;
}
