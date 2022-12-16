package com.jb.couponSystemPhaseTwo.clr.on.controllerTesting;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Component
@Order(13)
public class CustomerControllerTesting extends ControllerTesting implements CommandLineRunner {
    @Value("${customer_url}")
    private String url;
    private final Coupon coupon = Coupon.builder()
            .id(18).build();

    @Override
    public void run(String... args) throws Exception {
        controlDescription("\t\ttesting customerController\n");
        purchaseCoupon(3, coupon);
        getCustomerCoupons(3);
        getCustomerCoupons(3, Category.CARS);
        getCustomerCoupons(3, 85.7);
        getCustomerDetails(3);
        controlDescription("\t\ttesting customerController ended\n");
    }

    private void purchaseCoupon(int customerId, Coupon coupon) throws SQLException, CouponSystemException {
        HttpEntity<Coupon> add = new HttpEntity<>(coupon);
        try {
            ResponseEntity<Coupon> res = restTemplate.exchange(url + customerId + "/coupons/purchase", HttpMethod.POST, add, Coupon.class);
            successDescription("|--->\tcustomer purchaseCoupon success. response status is: " + (res.getStatusCodeValue()));
            getCustomerCoupons(customerId);
        } catch (Exception e) {
            failDescription("|--->\tcustomer purchaseCoupon fail");
            System.out.println(e.getMessage());
        }
    }

    private void getCustomerCoupons(int customerId) {
        ResponseEntity<List<Coupon>> res = restTemplate.exchange(url + customerId + "/coupons", HttpMethod.GET, null, new ParameterizedTypeReference<List<Coupon>>() {
        });
        controlDescription("|--->\tcustomer getCustomerCoupons. response status is: " + (res.getStatusCodeValue()));
        Objects.requireNonNull(res.getBody()).forEach(System.out::println);
    }

    private void getCustomerCoupons(int customerId, Category category) {
        ResponseEntity<List<Coupon>> res = restTemplate.exchange(url + customerId + "/coupons/byCategory?category=" + category, HttpMethod.GET, null, new ParameterizedTypeReference<List<Coupon>>() {
        });
        controlDescription("|--->\tcustomer getCustomerCoupons by category. response status is: " + (res.getStatusCodeValue()));
        Objects.requireNonNull(res.getBody()).forEach(System.out::println);
    }

    private void getCustomerCoupons(int customerId, double maxPrice) {
        ResponseEntity<List<Coupon>> res = restTemplate.exchange(url + customerId + "/coupons/byMaxPrice?maxPrice=" + maxPrice, HttpMethod.GET, null, new ParameterizedTypeReference<List<Coupon>>() {
        });
        controlDescription("|--->\tcustomer getCustomerCoupons by maxPrice. response status is: " + (res.getStatusCodeValue()));
        Objects.requireNonNull(res.getBody()).forEach(System.out::println);
    }

    private void getCustomerDetails(int customerId) {
        ResponseEntity<Customer> res = restTemplate.exchange(url + customerId + "/details", HttpMethod.GET, null, new ParameterizedTypeReference<Customer>() {
        });
        controlDescription("|--->\tcustomer getCustomerCoupons by maxPrice. response status is: " + (res.getStatusCodeValue()));
        System.out.println(res.getBody());
    }
}
