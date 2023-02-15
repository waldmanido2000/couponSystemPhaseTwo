package com.jb.couponSystemPhaseTwo.clr.on.serviceTesting;


import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
@Order(4)
public class CompanyServiceTesting extends ServicesTesting implements CommandLineRunner {
    private final int companyId = 1;
    private final Coupon coupon = Coupon.builder()
            .description("description...............")
            .price(130.4)
            .image("http://www.image.com")
            .category(Category.FOOD)
            .amount(49)
            .startDate(startDate)
            .endDate(endDate)
            .build();
    private final int couponId = 1;
    private static final Date startDate = Date.valueOf(LocalDate.now());
    private static final Date endDate = Date.valueOf(LocalDate.now().plusDays(12));

    @Override
    public void run(String... args) throws Exception {
        /* loginManager */
        controlDescription("\t\ttesting companyService\n");
        addCoupon();
        updateCoupon();
        deleteCoupon();
        getCompanyCoupons();
        getCompanyDetails();
        controlDescription("\t\ttesting companyService ended\n");
    }

    private void addCoupon() throws SQLException {

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
    }

    private void updateCoupon() throws SQLException {
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
    }

    private void deleteCoupon() throws SQLException {
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
    }

    private void getCompanyCoupons() throws SQLException {
        successDescription("|--->\tcompany getCompanyCoupons by category");
        companyService.getCompanyCoupons(companyId, Category.CLOTHES).forEach(System.out::println);

        successDescription("|--->\tcompany getCompanyCoupons by maxPrice");
        companyService.getCompanyCoupons(companyId, 150).forEach(System.out::println);

        successDescription("|--->\tcompany getCompanyDetails");
    }

    private void getCompanyDetails() throws SQLException {
        try {
            Company company = companyService.getCompanyDetails(companyId);
            System.out.printf("id = %s, name = %s, email = %s, password = %s\n company's coupon list:%n",
                    company.getId(), company.getName(), company.getEmail(), company.getPassword());

            company.getCoupons().forEach(System.out::println);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
    }
}

