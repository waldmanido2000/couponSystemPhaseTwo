package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.exceptions.ErrorMessage;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
public class CompanyServiceImpl extends ClientService implements CompanyService{
    @Override
    public boolean login(String email, String password) throws SQLException, CouponSystemException {
        if(companyRepo.existsByEmailAndPassword(email,password)){
            return true;
        }
        throw new CouponSystemException(ErrorMessage.LOGIN_FAIL);
    }

    @Override
    public void addCoupon(int companyId, Coupon coupon) throws SQLException, CouponSystemException {
        Company company = companyRepo.findById(companyId)
                .orElseThrow(()->new CouponSystemException(ErrorMessage.COMPANY_NOT_EXIST));
        coupon.setCompany(company);
        couponRepo.save(coupon);
    }

    @Override
    public void updateCoupon(int companyId, int couponId, Coupon coupon) throws SQLException, CouponSystemException {

    }

    @Override
    public void deleteCoupon(int companyId, int couponId) throws SQLException, CouponSystemException {

    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId) throws SQLException {
        return null;
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId, Category category) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws SQLException {
        return null;
    }

    @Override
    public Company getCompanyDetails(int companyId) throws SQLException {
        return null;
    }
}
