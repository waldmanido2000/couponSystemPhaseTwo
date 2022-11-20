package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.repos.CompanyRepo;
import com.jb.couponSystemPhaseTwo.repos.CouponRepo;
import com.jb.couponSystemPhaseTwo.repos.CustomerRepo;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientService {
    protected CompanyRepo companyRepo;
    protected CustomerRepo customerRepo;
    protected CouponRepo couponRepo;
    public abstract boolean login(String email, String password);
}
