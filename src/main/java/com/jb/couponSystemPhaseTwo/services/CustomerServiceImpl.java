package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.dto.LoginResDto;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.exceptions.ErrorMessage;
import com.jb.couponSystemPhaseTwo.exceptions.SecurityMessage;
import com.jb.couponSystemPhaseTwo.security.LoginInfo;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {
    @Override
    public LoginResDto login(String email, String password) throws CouponSecurityException {
        if (customerRepo.existsByEmailAndPassword(email, password)) {
            int customerId = customerRepo.findFirstByEmailAndPassword(email, password).getId();
            tokenService.addClient(customerId, ClientType.CUSTOMER);
            LoginInfo loginInfo = LoginInfo.builder()
                    .id(customerId)
                    .clientType(ClientType.CUSTOMER)
                    .time(LocalDateTime.now())
                    .build();
            UUID token = tokenService.getToken(loginInfo);
            return LoginResDto.builder()
                    .id(customerId)
                    .token(token)
                    .clientType(ClientType.CUSTOMER)
                    .build();
        }
        throw new CouponSecurityException(SecurityMessage.LOGIN_FAIL);
    }

    @Override
    public void purchaseCoupon(int customerId, Coupon coupon) throws CouponSystemException {
        coupon = couponRepo.findById(coupon.getId()).orElseThrow(() -> new CouponSystemException(ErrorMessage.COUPON_NOT_EXIST));
        if (couponRepo.isPurchased(customerId, coupon.getId()) != 0) {
            throw new CouponSystemException(ErrorMessage.COUPON_ALREADY_PURCHASED);
        }
        if (coupon.getAmount() == 0) {
            throw new CouponSystemException(ErrorMessage.COUPON_OUT_OF_STOCK);
        }
        if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException(ErrorMessage.COUPON_IS_OUT_DATED);
        }
        couponRepo.addPurchase(customerId, coupon.getId());
        coupon.setAmount(coupon.getAmount() - 1);
        couponRepo.saveAndFlush(coupon);
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId) {
        return couponRepo.findPurchasesByCustomer(customerId);
    }

    @Override
    public List<Coupon> getAvailableCoupons() {
        return couponRepo.findAll();
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId, Category category) {
        return couponRepo.findPurchasesByCustomerAndCategory(customerId, category.toString());
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId, double maxPrice) {
        return couponRepo.findPurchasesByCustomerAndMaxPrice(customerId, maxPrice);
    }

    @Override
    public Customer getCustomerDetails(int customerId) throws CouponSystemException {
        return customerRepo.findById(customerId)
                .orElseThrow(() -> new CouponSystemException(ErrorMessage.CUSTOMER_NOT_EXIST));
    }
}
