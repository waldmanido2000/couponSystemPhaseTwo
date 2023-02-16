package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.repos.CompanyRepo;
import com.jb.couponSystemPhaseTwo.repos.CouponRepo;
import com.jb.couponSystemPhaseTwo.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
public abstract class ClientService {
    @Autowired
    protected CompanyRepo companyRepo;
    @Autowired
    protected CustomerRepo customerRepo;
    @Autowired
    protected CouponRepo couponRepo;
    @Autowired
    protected TokenServiceImpl tokenService;

    public abstract UUID login(String email, String password) throws SQLException, CouponSecurityException;
}
