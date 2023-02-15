package com.jb.couponSystemPhaseTwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.jb.couponSystemPhaseTwo"}, excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.jb.couponSystemPhaseTwo.clr.off.*"))

public class CouponSystemPhaseTwoApplication {


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(CouponSystemPhaseTwoApplication.class, args);
    }
}
