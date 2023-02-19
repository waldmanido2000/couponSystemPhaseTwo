package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.dto.LoginReqDto;
import com.jb.couponSystemPhaseTwo.dto.LoginResDto;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.exceptions.SecurityMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class LoginManager {
    @Autowired
    @Qualifier("companyServiceImpl")
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    @Qualifier("adminServiceImpl")
    private AdminService adminService;

    public LoginResDto login(LoginReqDto loginReqDto) throws SQLException, CouponSecurityException {
        ClientService clientService = null;
        boolean success;
        switch (loginReqDto.getClientType().ordinal()) {
            case 0:
                System.out.println("AdminService is being used.");
                clientService = (ClientService) adminService;
                break;
            case 1:
                System.out.println("CompanyService is being used.");
                clientService = (ClientService) companyService;
                break;
            case 2:
                System.out.println("CustomerService is being used.");
                clientService = (ClientService) customerService;
                break;
        }
        if (clientService == null) {
            throw new CouponSecurityException(SecurityMessage.LOGIN_FAIL);
        }

        return clientService.login(loginReqDto.getEmail(), loginReqDto.getPassword());
    }
}
