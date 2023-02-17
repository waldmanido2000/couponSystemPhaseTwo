package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.exceptions.SecurityMessage;
import com.jb.couponSystemPhaseTwo.services.ClientType;
import com.jb.couponSystemPhaseTwo.services.CustomerService;
import com.jb.couponSystemPhaseTwo.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customer")
@CrossOrigin(origins = "*")
public class CustomerControllerImpl implements CustomerController {
    @Autowired
    @Qualifier("customerServiceImpl")
    private CustomerService customerServiceImpl;
    @Autowired
    private TokenService tokenService;

    @Override
    @PostMapping("{customerId}/coupons/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseCoupon(@PathVariable int customerId, @RequestBody Coupon coupon, @RequestHeader("Authorization") UUID token) throws CouponSystemException, SQLException, CouponSecurityException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        int idFromMap = tokenService.getClientId(token);
        System.out.println(idFromMap);
        if (idFromMap != customerId) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        customerServiceImpl.purchaseCoupon(customerId, coupon);
    }

    @GetMapping("/coupons/available")
    public List<Coupon> getAvailableCoupons(@RequestHeader("Authorization") UUID token) throws SQLException, CouponSecurityException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        return customerServiceImpl.getAvailableCoupons();
    }

    @GetMapping("{customerId}/coupons")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId, @RequestHeader("Authorization") UUID token) throws SQLException, CouponSecurityException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        int idFromMap = tokenService.getClientId(token);
        System.out.println(idFromMap);
        if (idFromMap != customerId) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        return customerServiceImpl.getCustomerCoupons(customerId);
    }

    @Override
    @GetMapping("{customerId}/coupons/byCategory")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId, @RequestParam Category category, @RequestHeader("Authorization") UUID token) throws SQLException, CouponSecurityException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        int idFromMap = tokenService.getClientId(token);
        System.out.println(idFromMap);
        if (idFromMap != customerId) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        return customerServiceImpl.getCustomerCoupons(customerId, category);
    }

    @Override
    @GetMapping("{customerId}/coupons/byMaxPrice")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId, @RequestParam double maxPrice, @RequestHeader("Authorization") UUID token) throws SQLException, CouponSecurityException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        int idFromMap = tokenService.getClientId(token);
        System.out.println(idFromMap);
        if (idFromMap != customerId) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        return customerServiceImpl.getCustomerCoupons(customerId, maxPrice);
    }

    @Override
    @GetMapping("{customerId}/details")
    public Customer getCustomerDetails(@PathVariable int customerId, @RequestHeader("Authorization") UUID token) throws CouponSystemException, SQLException, CouponSecurityException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        int idFromMap = tokenService.getClientId(token);
        System.out.println(idFromMap);
        if (idFromMap != customerId) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        return customerServiceImpl.getCustomerDetails(customerId);
    }

    private boolean correctUser(int reqId, int mapId) throws CouponSecurityException {
        if (reqId != mapId) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        return true;
    }
}
