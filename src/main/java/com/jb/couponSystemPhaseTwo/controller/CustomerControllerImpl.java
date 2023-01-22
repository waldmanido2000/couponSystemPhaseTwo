package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/customer")
@CrossOrigin(origins = "*")
public class CustomerControllerImpl implements CustomerController {
    @Autowired
    @Qualifier("customerServiceImpl")
    private CustomerService customerServiceImpl;

    @Override
    @PostMapping("{customerId}/coupons/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseCoupon(@PathVariable int customerId, @RequestBody Coupon coupon) throws CouponSystemException, SQLException {
        customerServiceImpl.purchaseCoupon(customerId, coupon);
    }

    @Override
    @GetMapping("{customerId}/coupons")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId) throws SQLException {
        return customerServiceImpl.getCustomerCoupons(customerId);
    }

    @Override
    @GetMapping("{customerId}/coupons/byCategory")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId, @RequestParam Category category) throws SQLException {
        return customerServiceImpl.getCustomerCoupons(customerId, category);
    }

    @Override
    @GetMapping("{customerId}/coupons/byMaxPrice")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId, @RequestParam double maxPrice) throws SQLException {
        return customerServiceImpl.getCustomerCoupons(customerId, maxPrice);
    }

    @Override
    @GetMapping("{customerId}/details")
    public Customer getCustomerDetails(@PathVariable int customerId) throws CouponSystemException, SQLException {
        return customerServiceImpl.getCustomerDetails(customerId);
    }
}
