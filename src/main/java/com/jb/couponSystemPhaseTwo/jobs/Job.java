package com.jb.couponSystemPhaseTwo.jobs;

import com.jb.couponSystemPhaseTwo.repos.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class Job {
    @Autowired
    private CouponRepo couponRepo;
//    @Scheduled(cron = "*/5 * * * * *")
    @Scheduled(cron = "0 0 2 * * *")
    public void deleteObsoleteCoupons(){
        Date today = Date.valueOf(LocalDate.now());
        System.out.println("running job");
        couponRepo.deleteObsoletePurchases(today);
        couponRepo.deleteObsoleteCoupons(today);
    }
}
