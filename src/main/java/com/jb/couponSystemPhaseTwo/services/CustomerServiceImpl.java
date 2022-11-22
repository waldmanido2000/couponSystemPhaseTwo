package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.exceptions.ErrorMessage;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class CustomerServiceImpl  extends ClientService implements CustomerService{
    @Override
    public boolean login(String email, String password) throws SQLException, CouponSystemException {
        if(customerRepo.existsByEmailAndPassword(email,password)){
            return true;
        }
        throw new CouponSystemException(ErrorMessage.LOGIN_FAIL);
    }

    @Override
    public void purchaseCoupon(int customerId, Coupon coupon) throws SQLException, CouponSystemException {

    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId) throws SQLException {
        return null;
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId, Category category) throws SQLException {
        return null;
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId, double maxPrice) throws SQLException {
        return null;
    }

    @Override
    public Customer getCustomerDetails(int customerId) throws SQLException {
        return null;
    }
}
