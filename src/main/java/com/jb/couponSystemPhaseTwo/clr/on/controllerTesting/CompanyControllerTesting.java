package com.jb.couponSystemPhaseTwo.clr.on.controllerTesting;

import com.jb.couponSystemPhaseTwo.beans.Category;
import com.jb.couponSystemPhaseTwo.beans.Company;
import com.jb.couponSystemPhaseTwo.beans.Coupon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
@Order(12)
public class CompanyControllerTesting extends ControllerTesting implements CommandLineRunner {
    @Value("${company_url}")
    private String url;
    private final Coupon coupon = Coupon.builder()
            .amount(230)
            .category(Category.CARS)
            .description("this is the description")
            .endDate(Date.valueOf(LocalDate.now().plusDays(7)))
            .image("http://blablabla.jpg")
            .price(120)
            .startDate(Date.valueOf(LocalDate.now().minusDays(6)))
            .title("restTemplate new").build();
    private final Coupon couponToUpdate = Coupon.builder()
            .amount(230)
            .category(Category.CARS)
            .description("this is the description")
            .endDate(Date.valueOf(LocalDate.now().plusDays(7)))
            .image("http://blablabla.jpg")
            .price(120)
            .startDate(Date.valueOf(LocalDate.now().minusDays(6)))
            .title("restTemplate update").build();

    @Override
    public void run(String... args) throws Exception {
        controlDescription("\t\ttesting companyController\n");
        int companyId = 1;
        getCompanyCoupons(companyId);
        addCoupon(companyId, coupon);
        int couponId = 1;
        updateCoupon(companyId, 2, couponToUpdate);
        deleteCoupon(companyId, couponId);
        getCompanyCoupons(companyId, Category.ELECTRICITY);
        getCompanyCoupons(companyId, 170.5);
        getCompanyDetails(companyId);
        controlDescription("\t\ttesting companyController ended\n");
    }

    private void addCoupon(int companyId, Coupon coupon) {
        HttpEntity<Coupon> add = new HttpEntity<>(coupon);
        try {
            ResponseEntity<Coupon> res = restTemplate.exchange(url + companyId + "/coupons", HttpMethod.POST, add, Coupon.class);
            successDescription("|--->\tcompany addCoupon success. response status is: " + (res.getStatusCodeValue()));
            getCompanyCoupons(companyId);
        } catch (Exception e) {
            failDescription("|--->\tcompany addCoupon fail");
            System.out.println(e.getMessage());
        }
    }

    private void updateCoupon(int companyId, int couponId, Coupon coupon) {
        coupon.setId(couponId);
        try {
            HttpEntity<Coupon> update = new HttpEntity<>(coupon);
            ResponseEntity<Coupon> res = restTemplate.exchange(url + companyId + "/coupon/" + couponId, HttpMethod.PUT, update, Coupon.class);
            successDescription("|--->\tcompany updateCoupon success. response status is: " + (res.getStatusCodeValue()));
            getCompanyCoupons(companyId);
        } catch (Exception e) {
            failDescription("|--->\tcompany updateCompany fail");
            System.out.println(e.getMessage());
        }
    }

    private void deleteCoupon(int companyId, int couponId) {
        try {
            ResponseEntity<Coupon> res = restTemplate.exchange(url + companyId + "/coupon/" + couponId, HttpMethod.DELETE, null, Coupon.class);
            successDescription("|--->\tcompany deleteCoupon success. response status is: " + (res.getStatusCodeValue()));
            getCompanyCoupons(companyId);
        } catch (Exception e) {
            failDescription("|--->\tcompany deleteCoupon fail");
            System.out.println(e.getMessage());
        }

    }

    private void getCompanyCoupons(int companyId) {
        ResponseEntity<List<Coupon>> res = restTemplate.exchange(url + companyId + "/coupons", HttpMethod.GET, null, new ParameterizedTypeReference<List<Coupon>>() {
        });
        controlDescription("|--->\tcompany getCompanyCoupons. response status is: " + (res.getStatusCodeValue()));
        Objects.requireNonNull(res.getBody()).forEach(System.out::println);
    }

    private void getCompanyCoupons(int companyId, Category category) {
        ResponseEntity<List<Coupon>> res = restTemplate.exchange(url + companyId + "/coupons/byCategory?category=" + category, HttpMethod.GET, null, new ParameterizedTypeReference<List<Coupon>>() {
        });
        controlDescription("|--->\tcompany getCompanyCoupons by category. response status is: " + (res.getStatusCodeValue()));
        Objects.requireNonNull(res.getBody()).forEach(System.out::println);
    }

    private void getCompanyCoupons(int companyId, double maxPrice) {
        ResponseEntity<List<Coupon>> res = restTemplate.exchange(url + companyId + "/coupons/byMaxPrice?maxPrice=" + maxPrice, HttpMethod.GET, null, new ParameterizedTypeReference<List<Coupon>>() {
        });
        controlDescription("|--->\tcompany getCompanyCoupons by maxPrice. response status is: " + (res.getStatusCodeValue()));
        Objects.requireNonNull(res.getBody()).forEach(System.out::println);
    }

    private void getCompanyDetails(int companyId) {
        ResponseEntity<Company> res = restTemplate.exchange(url + companyId + "/details", HttpMethod.GET, null, new ParameterizedTypeReference<Company>() {
        });
        controlDescription("|--->\tcompany getCompanyCoupons by maxPrice. response status is: " + (res.getStatusCodeValue()));
        System.out.println(res.getBody());
    }
}
