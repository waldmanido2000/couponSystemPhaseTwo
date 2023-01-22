package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/company")
@CrossOrigin(origins = "*")
public class CompanyControllerImpl implements CompanyController {
    @Autowired
    @Qualifier("companyServiceImpl")
    private CompanyService companyService;

    @Override
    @PostMapping("{companyId}/coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@PathVariable int companyId, @RequestBody Coupon coupon) throws CouponSystemException, SQLException {
        companyService.addCoupon(companyId, coupon);
    }

    @Override
    @PutMapping("{companyId}/coupon/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCoupon(@PathVariable int companyId, @PathVariable int couponId, @RequestBody Coupon coupon) throws CouponSystemException, SQLException {
        companyService.updateCoupon(companyId, couponId, coupon);
    }

    @Override
    @DeleteMapping("{companyId}/coupon/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@PathVariable int companyId, @PathVariable int couponId) throws CouponSystemException, SQLException {
        companyService.deleteCoupon(companyId, couponId);
    }

    @Override
    @GetMapping("{companyId}/coupons")
    public List<Coupon> getCompanyCoupons(@PathVariable int companyId) throws SQLException {
        return companyService.getCompanyCoupons(companyId);
    }

    @Override
    @GetMapping("{companyId}/coupons/byCategory")
    public List<Coupon> getCompanyCoupons(@PathVariable int companyId, @RequestParam Category category) throws SQLException {
        return companyService.getCompanyCoupons(companyId, category);
    }

    @GetMapping("{companyId}/coupons/byMaxPrice")
    public List<Coupon> getCompanyCoupons(@PathVariable int companyId, @RequestParam double maxPrice) throws SQLException {
        return companyService.getCompanyCoupons(companyId, maxPrice);
    }

    @Override
    @GetMapping("{companyId}/details")
    public Company getCompanyDetails(@PathVariable int companyId) throws CouponSystemException, SQLException {
        return companyService.getCompanyDetails(companyId);
    }
}
