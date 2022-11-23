package com.jb.couponSystemPhaseTwo.clr.on;


import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.services.*;
import com.jb.couponSystemPhaseTwo.utils.Debug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
@Order(4)
public class CompanyServiceTesting implements CommandLineRunner {
    /* testing legend attributes */
    private static final String FAIL = AnticipatedResult.FAIL.getMessage();
    private static final String CONTROL = AnticipatedResult.CONTROL.getMessage();
    private static final String SUCCESS = AnticipatedResult.SUCCESS.getMessage();
    private static final String RESET_TEXT = AnticipatedResult.RESET.getMessage();
    private static final String FLAG_NAME = "TEST_DEBUG_MODE";

    private static final Date startDate = Date.valueOf(LocalDate.now());
    private static final Date endDate = Date.valueOf(LocalDate.now().plusDays(12));

    @Autowired
    private CompanyService companyService;

    @Override
    public void run(String... args) throws Exception {
        /* loginManager */
        controlDescription("\t\ttesting companyService\n");
        companyServiceTest();

    }

    private void companyServiceTest() throws SQLException {

        int companyId = 1;
        Coupon coupon = Coupon.builder()
                .description("description...............")
                .price(130.4)
                .image("http://www.image.com")
                .category(Category.FOOD)
                .amount(49)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        double maxPrice = 150;
        int couponId = 1;
        Category category = Category.CLOTHES;

        controlDescription("|--->\tcompany coupons");
        companyService.getCompanyCoupons(companyId).forEach(System.out::println);
        failDescription("|--->\tcompany addCoupon (already exists)");
        coupon.setTitle("title 1");
        try {
            companyService.addCoupon(companyId, coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        successDescription("|--->\tcompany addCoupon success");
        coupon.setTitle("new coupon");
        try {
            companyService.addCoupon(companyId, coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        controlDescription("|--->\tcompany coupons");
        companyService.getCompanyCoupons(companyId).forEach(System.out::println);

        failDescription("|--->\tcompany updateCoupon (wrong coupon id)");
        try {
            companyService.updateCoupon(companyId, 500, coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        successDescription("|--->\tcompany updateCoupon");
        coupon.setTitle("updated title");
        try {
            companyService.updateCoupon(companyId, 101, coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        controlDescription("|--->\tcompany coupons");
        companyService.getCompanyCoupons(companyId).forEach(System.out::println);

        failDescription("|--->\tcompany deleteCoupon (not exist)");
        try {
            companyService.deleteCoupon(companyId, 800);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        successDescription("|--->\tcompany deleteCoupon success");
        try {
            companyService.deleteCoupon(companyId, 10);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        controlDescription("|--->\tcompany coupons");
        companyService.getCompanyCoupons(companyId).forEach(System.out::println);

        successDescription("|--->\tcompany getCompanyCoupons by category");
        companyService.getCompanyCoupons(companyId, category).forEach(System.out::println);

        successDescription("|--->\tcompany getCompanyCoupons by maxPrice");
        companyService.getCompanyCoupons(companyId, maxPrice).forEach(System.out::println);

        successDescription("|--->\tcompany getCompanyDetails");
        try {
            Company company = companyService.getCompanyDetails(companyId);
            System.out.println("company.getId() = " + company.getId());
            System.out.println("company.getName() = " + company.getName());
            System.out.println("company.getEmail() = " + company.getEmail());
            System.out.println("company.getPassword() = " + company.getPassword());
            company.getCoupons().forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
    }

    private void failDescription(String description) {
        Debug.showDescription(FLAG_NAME, FAIL + description + "\t\t" + RESET_TEXT);
    }

    private void successDescription(String description) {
        Debug.showDescription(FLAG_NAME, SUCCESS + description + "\t\t" + RESET_TEXT);
    }

    private void controlDescription(String description) {
        Debug.showDescription(FLAG_NAME, CONTROL + description + "\t\t" + RESET_TEXT);
    }
}

