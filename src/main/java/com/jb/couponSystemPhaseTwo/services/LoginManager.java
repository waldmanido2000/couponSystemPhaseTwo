package com.jb.couponSystemPhaseTwo.services;

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

    public ClientService login(String email, String password, ClientType clientType) throws SQLException, CouponSecurityException {
        ClientService clientService = null;
        boolean success;
        switch (clientType.ordinal()) {
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

        success = clientService.login(email, password);

        if (success) {
            System.out.println("login success (LoginManager)");
            return clientService;
        }

        throw new CouponSecurityException(SecurityMessage.LOGIN_FAIL);
    }
}
