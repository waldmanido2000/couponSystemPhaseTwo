package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.exceptions.SecurityMessage;
import com.jb.couponSystemPhaseTwo.services.ClientType;
import com.jb.couponSystemPhaseTwo.services.CompanyService;
import com.jb.couponSystemPhaseTwo.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/company")
@CrossOrigin(origins = "*")
public class CompanyControllerImpl implements CompanyController {
    @Autowired
    @Qualifier("companyServiceImpl")
    private CompanyService companyService;
    @Autowired
    private TokenService tokenService;

    @Override
    @PostMapping("{companyId}/coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@PathVariable int companyId, @RequestBody Coupon coupon, @RequestHeader("Authorization") UUID token) throws CouponSystemException, SQLException, CouponSecurityException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        int idFromMap = tokenService.getClientId(token);
        System.out.println(idFromMap);
        if (idFromMap != companyId) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        companyService.addCoupon(companyId, coupon);
    }

    @Override
    @PutMapping("{companyId}/coupon/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCoupon(@PathVariable int companyId, @PathVariable int couponId, @RequestBody Coupon coupon, @RequestHeader("Authorization") UUID token) throws CouponSystemException, SQLException, CouponSecurityException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        int idFromMap = tokenService.getClientId(token);
        System.out.println(idFromMap);
        if (idFromMap != companyId) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        companyService.updateCoupon(companyId, couponId, coupon);
    }

    @Override
    @DeleteMapping("{companyId}/coupon/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@PathVariable int companyId, @PathVariable int couponId, @RequestHeader("Authorization") UUID token) throws CouponSystemException, SQLException, CouponSecurityException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        int idFromMap = tokenService.getClientId(token);
        System.out.println(idFromMap);
        if (idFromMap != companyId) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        companyService.deleteCoupon(companyId, couponId);
    }

    @Override
    @GetMapping("{companyId}/coupons")
    public List<Coupon> getCompanyCoupons(@PathVariable int companyId, @RequestHeader("Authorization") UUID token) throws SQLException, CouponSecurityException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        int idFromMap = tokenService.getClientId(token);
        System.out.println(idFromMap);
        if (idFromMap != companyId) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        return companyService.getCompanyCoupons(companyId);
    }

    @Override
    @GetMapping("{companyId}/coupons/byCategory")
    public List<Coupon> getCompanyCoupons(@PathVariable int companyId, @RequestParam Category category, @RequestHeader("Authorization") UUID token) throws SQLException, CouponSecurityException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        int idFromMap = tokenService.getClientId(token);
        System.out.println(idFromMap);
        if (idFromMap != companyId) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        return companyService.getCompanyCoupons(companyId, category);
    }

    @GetMapping("{companyId}/coupons/byMaxPrice")
    public List<Coupon> getCompanyCoupons(@PathVariable int companyId, @RequestParam double maxPrice, @RequestHeader("Authorization") UUID token) throws SQLException, CouponSecurityException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        int idFromMap = tokenService.getClientId(token);
        System.out.println(idFromMap);
        if (idFromMap != companyId) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        return companyService.getCompanyCoupons(companyId, maxPrice);
    }

    @Override
    @GetMapping("{companyId}/details")
    public Company getCompanyDetails(@PathVariable int companyId, @RequestHeader("Authorization") UUID token) throws CouponSystemException, SQLException, CouponSecurityException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        int idFromMap = tokenService.getClientId(token);
        System.out.println(idFromMap);
        if (idFromMap != companyId) {
            throw new CouponSecurityException(SecurityMessage.RESTRICTED_AREA);
        }
        return companyService.getCompanyDetails(companyId);
    }
}
