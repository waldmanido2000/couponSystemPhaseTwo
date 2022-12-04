package com.jb.couponSystemPhaseTwo.clr.on.serviceTesting;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
@Order(5)
public class CustomerServiceTesting extends ServicesTesting implements CommandLineRunner {
    private final int customerId = 1;
    private final Coupon coupon = Coupon.builder()
            .description("description...............")
            .price(130.4)
            .image("http://www.image.com")
            .category(Category.FOOD)
            .amount(0)
            .startDate(startDate)
            .endDate(endDate)
            .build();
    private final int couponId = 1;
    private final Category category = Category.RESTAURANT;
    private static final Date startDate = Date.valueOf(LocalDate.now());
    private static final Date endDate = Date.valueOf(LocalDate.now().plusDays(12));

    @Override
    public void run(String... args) throws Exception {
        controlDescription("\t\ttesting customerService\n");
        purchaseCoupon(customerId, coupon);
        getCustomerCoupons(customerId, category);
        getCustomerCoupons(customerId, 150);
        getCustomerDetails(customerId);
        controlDescription("\t\ttesting customerService ended\n");
    }

    private void purchaseCoupon(int customerId, Coupon coupon) throws SQLException {
        controlDescription("|--->\tcoupons");
        failDescription("|--->\tcustomer purchaseCoupon (coupon already purchased)");
        customerService.getCustomerCoupons(customerId).forEach(System.out::println);
        coupon.setId(6);
        try {
            customerService.purchaseCoupon(customerId,coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        failDescription("|--->\tcustomer purchaseCoupon (coupon amount 0)");
        customerService.getCustomerCoupons(customerId).forEach(System.out::println);
        coupon.setId(11);
        try {
            customerService.purchaseCoupon(customerId,coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        failDescription("|--->\tcustomer purchaseCoupon (coupon is obsolete)");
        customerService.getCustomerCoupons(customerId).forEach(System.out::println);
        coupon.setId(13);
        try {
            customerService.purchaseCoupon(customerId,coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        successDescription("|--->\tcustomer purchaseCoupon success");
        customerService.getCustomerCoupons(customerId).forEach(System.out::println);
        coupon.setId(couponId);
        try {
            customerService.purchaseCoupon(customerId,coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        controlDescription("|--->\tcoupons");
        customerService.getCustomerCoupons(customerId).forEach(System.out::println);
    }

    private void getCustomerCoupons(int customerId, Category category) throws SQLException {
        successDescription("|--->\tCoupons by category success");
        customerService.getCustomerCoupons(customerId, category).forEach(System.out::println);
    }
    private void getCustomerCoupons(int customerId, double maxPrice) throws SQLException {
        successDescription("|--->\tCoupons by max price success");
        customerService.getCustomerCoupons(customerId, maxPrice).forEach(System.out::println);
    }
    private void getCustomerDetails(int customerId) throws SQLException {
        successDescription("|--->\tcustomer get details success");
        try {
            Customer customer = customerService.getCustomerDetails(customerId);
            System.out.printf("id = %s, first name = %s, last name = %s, email = %s, password = %s\n",
                    customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPassword());
            customer.getCoupons().forEach(System.out::println);
        }
        catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
    }
}
