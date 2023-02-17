package com.jb.couponSystemPhaseTwo.clr.on.controllerTesting;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import com.jb.couponSystemPhaseTwo.beans.Customer;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

//@Component
@Order(13)
public class CustomerControllerTesting extends ControllerTesting implements CommandLineRunner {
    @Value("${customer_url}")
    private String url;
    @Autowired
    private TokenService tokenService;
    private final Coupon coupon = Coupon.builder().id(18).build();
    private final HttpHeaders headers = new HttpHeaders();

    public CustomerControllerTesting() {
//        UUID token = tokenService.addClient(3, ClientType.CUSTOMER);
        String token = "0d0eca18-c597-4cfa-8fac-9e1ab03e1b96";
        this.headers.set("Authorization", token.toString());
    }

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
        HttpEntity<Coupon> add = new HttpEntity<>(coupon, this.headers);
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
        HttpEntity<Void> httpEntity = new HttpEntity<>(this.headers);
        ResponseEntity<List<Coupon>> res = restTemplate.exchange(url + customerId + "/coupons", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Coupon>>() {
        });
        controlDescription("|--->\tcustomer getCustomerCoupons. response status is: " + (res.getStatusCodeValue()));
        Objects.requireNonNull(res.getBody()).forEach(System.out::println);
    }

    private void getCustomerCoupons(int customerId, Category category) {
        try {
            HttpEntity<Void> httpEntity = new HttpEntity<>(this.headers);
            ResponseEntity<List<Coupon>> res = restTemplate.exchange(url + customerId + "/coupons/byCategory?category=" + category, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Coupon>>() {
            });
            successDescription("|--->\tcustomer getCustomerCoupons by category success. response status is: " + (res.getStatusCodeValue()));
            Objects.requireNonNull(res.getBody()).forEach(System.out::println);
        } catch (Exception e) {
            failDescription("|--->\tcustomer getCustomerCoupons by category fail");
            System.out.println(e.getMessage());
        }
    }

    private void getCustomerCoupons(int customerId, double maxPrice) {
        try {
            HttpEntity<Void> httpEntity = new HttpEntity<>(this.headers);
            ResponseEntity<List<Coupon>> res = restTemplate.exchange(url + customerId + "/coupons/byMaxPrice?maxPrice=" + maxPrice, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Coupon>>() {
            });
            successDescription("|--->\tcustomer getCustomerCoupons by maxPrice success. response status is: " + (res.getStatusCodeValue()));
            Objects.requireNonNull(res.getBody()).forEach(System.out::println);
        } catch (Exception e) {
            failDescription("|--->\tcustomer getCustomerCoupons by maxPrice fail");
            System.out.println(e.getMessage());
        }
    }

    private void getCustomerDetails(int customerId) {
        try {
            HttpEntity<Void> httpEntity = new HttpEntity<>(this.headers);
            ResponseEntity<Customer> res = restTemplate.exchange(url + customerId + "/details", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Customer>() {
            });
            successDescription("|--->\tcustomer getCustomerDetails success. response status is: " + (res.getStatusCodeValue()));
            System.out.println(res.getBody());
        } catch (Exception e) {
            failDescription("|--->\tcustomer getCustomerDetails fail");
            System.out.println(e.getMessage());
        }
    }
}