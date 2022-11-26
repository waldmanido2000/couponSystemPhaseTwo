package com.jb.couponSystemPhaseTwo.clr.on;

import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.services.*;
import com.jb.couponSystemPhaseTwo.utils.Debug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@Order(2)
public class CouponSystemTesting extends ServicesTesting implements CommandLineRunner {

    /* login attributes */
    private static final String WRONG_EMAIL = "wrong@email.com";
    private static final String WRONG_PASSWORD = "WRONG";
    private static final String ADMIN_EMAIL = "admin@admin.com";
    private static final String ADMIN_PASSWORD = "admin";
    private static final String COMPANY_EMAIL = "www@company8";
    private static final String COMPANY_PASSWORD = "pass8";
    private static final String CUSTOMER_EMAIL = "www@customer4";
    private static final String CUSTOMER_PASSWORD = "pass4";
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    @Qualifier("adminServiceImpl")
    private AdminService adminService;
    @Autowired
    private LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        /* loginManager */
        controlDescription("\t\ttesting loginManager\n");
        adminLogin();
        companyLogin();
        custmoerLogin();
        controlDescription("\t\ttesting loginManager ended\n");
    }

    private void adminLogin() throws SQLException, CouponSystemException {
        ClientService clientService;
        failDescription("|--->\tadmin login testing wrong details");
        clientService = testLogin(WRONG_EMAIL, WRONG_PASSWORD, ClientType.ADMINISTRATOR);
        failDescription("|--->\tadmin login testing wrong Client type");
        clientService = testLogin(ADMIN_EMAIL, ADMIN_PASSWORD, ClientType.CUSTOMER);
        successDescription("|--->\tadmin login testing success");
        clientService = testLogin(ADMIN_EMAIL, ADMIN_PASSWORD, ClientType.ADMINISTRATOR);
    }
    private void companyLogin() throws SQLException, CouponSystemException {
        ClientService clientService;
        failDescription("|--->\tcompany login testing wrong details");
        clientService = testLogin(WRONG_EMAIL, WRONG_PASSWORD, ClientType.COMPANY);
        failDescription("|--->\tcompany login testing wrong Client type");
        clientService = testLogin(COMPANY_EMAIL, COMPANY_PASSWORD, ClientType.CUSTOMER);
        successDescription("|--->\tcompany login testing success");
        clientService = testLogin(COMPANY_EMAIL, COMPANY_PASSWORD, ClientType.COMPANY);
    }
    private void custmoerLogin() throws SQLException, CouponSystemException {
        ClientService clientService;
        failDescription("|--->\tcustomer login testing wrong details");
        clientService = testLogin(WRONG_EMAIL, WRONG_PASSWORD, ClientType.CUSTOMER);
        failDescription("|--->\tcustomer login testing wrong Client type");
        clientService = testLogin(CUSTOMER_EMAIL, CUSTOMER_PASSWORD, ClientType.ADMINISTRATOR);
        successDescription("|--->\tcustomer login testing success");
        clientService = testLogin(CUSTOMER_EMAIL, CUSTOMER_PASSWORD, ClientType.CUSTOMER);
    }
    private ClientService testLogin(String email, String password, ClientType clientType) throws SQLException {
        try {
            return loginManager.login(email, password, clientType);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

