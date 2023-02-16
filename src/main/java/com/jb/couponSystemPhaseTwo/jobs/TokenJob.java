package com.jb.couponSystemPhaseTwo.jobs;

import com.jb.couponSystemPhaseTwo.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TokenJob {
    @Autowired
    private TokenService tokenService;

    @Scheduled(fixedRate = 60000) // Run every 60 seconds
    public void deleteObsoleteTokens() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("running TokenJob");
        tokenService.clearExpiredTokens(now);
    }
}
