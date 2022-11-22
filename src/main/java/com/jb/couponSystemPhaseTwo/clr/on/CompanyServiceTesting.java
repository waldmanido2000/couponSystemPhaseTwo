package com.jb.couponSystemPhaseTwo.clr.on;


import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.services.*;
import com.jb.couponSystemPhaseTwo.utils.Debug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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

    private void companyServiceTest() throws SQLException, CouponSystemException {

        int companyId = 1;
        Coupon coupon = Coupon.builder()
                .description("description...............")
                .title("title ..............")
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


        failDescription("|--->\tcompany addCoupon");
        companyService.addCoupon(companyId, coupon);// TODO: 11/22/2022
        successDescription("|--->\tcompany addCoupon");
        companyService.addCoupon(companyId, coupon);

        failDescription("|--->\tcompany updateCoupon");// TODO: 11/22/2022  
        companyService.updateCoupon(companyId, companyId, coupon);
        successDescription("|--->\tcompany updateCoupon");
        companyService.updateCoupon(companyId, companyId, coupon);

        failDescription("|--->\tcompany deleteCoupon");// TODO: 11/22/2022  
        companyService.deleteCoupon(companyId, couponId);
        successDescription("|--->\tcompany add coupon");
        companyService.deleteCoupon(companyId, couponId);

        failDescription("|--->\tcompany getCompanyCoupons");// TODO: 11/22/2022  
        companyService.getCompanyCoupons(companyId);
        successDescription("|--->\tcompany getCompanyCoupons");
        companyService.getCompanyCoupons(companyId);

        failDescription("|--->\tcompany getCompanyCoupons");// TODO: 11/22/2022  
        companyService.getCompanyCoupons(companyId, category);
        successDescription("|--->\tcompany getCompanyCoupons");
        companyService.getCompanyCoupons(companyId, category);

        failDescription("|--->\tcompany getCompanyCoupons");// TODO: 11/22/2022  
        companyService.getCompanyCoupons(companyId, maxPrice);
        successDescription("|--->\tcompany getCompanyCoupons");
        companyService.getCompanyCoupons(companyId, maxPrice);

        failDescription("|--->\tcompany getCompanyDetails");// TODO: 11/22/2022
        companyService.getCompanyDetails(companyId);
        successDescription("|--->\tcompany getCompanyDetails");
        companyService.getCompanyDetails(companyId);

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

