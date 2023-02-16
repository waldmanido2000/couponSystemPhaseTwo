package com.jb.couponSystemPhaseTwo.clr.on.serviceTesting;

import com.jb.couponSystemPhaseTwo.dto.LoginReqDto;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSystemException;
import com.jb.couponSystemPhaseTwo.services.*;
import com.jb.couponSystemPhaseTwo.utils.MessageColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.UUID;

@Component
@Order(2)
public class CouponSystemTesting extends ServicesTesting implements CommandLineRunner {
    private String banner = MessageColor.ANSI_BG_PURPLE.getTextColor() + MessageColor.ANSI_BLACK.getTextColor() +
            "\t\t\t\t  __                   __  .__                                           .__                     \n" +
            "\t\t\t\t_/  |_  ____   _______/  |_|__| ____    ____     ______ ______________  _|__| ____  ____   ______\n" +
            "\t\t\t\t\\   __\\/ __ \\ /  ___/\\   __\\  |/    \\  / ___\\   /  ___// __ \\_  ___\\  \\/ /  |/ ___\\/ __ \\ /  ___/\n" +
            "\t\t\t\t |  | \\  ___/ \\___ \\  |  | |  |   |  \\/ /_/  >  \\___ \\\\  ___/|  |   \\   /|  \\  \\__\\  ___/ \\___ \\ \n" +
            "\t\t\t\t |__|  \\___  >____  > |__| |__|___|  /\\___  /  /____  >\\___  >__|    \\_/ |__|\\___  >___  >____  >\n" +
            "\t\t\t\t           \\/     \\/               \\//_____/        \\/     \\/                    \\/    \\/     \\/ \n\n"+
            MessageColor.ANSI_BG_BLACK.getTextColor();

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
    @Qualifier("companyServiceImpl")
    private CompanyService companyService;
    @Autowired
    @Qualifier("customerServiceImpl")
    private CustomerService customerService;
    @Autowired
    @Qualifier("adminServiceImpl")
    private AdminService adminService;
    @Autowired
    private LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        /* loginManager */
        System.out.println(banner);
        controlDescription("\t\ttesting loginManager\n");
        adminLogin();
        companyLogin();
        custmoerLogin();
        controlDescription("\t\ttesting loginManager ended\n");
    }

    private void adminLogin() throws SQLException, CouponSystemException {
        LoginReqDto wrongCredentials = LoginReqDto.builder()
                .email(WRONG_EMAIL)
                .password(WRONG_PASSWORD)
                .clientType(ClientType.ADMINISTRATOR)
                .build();
        LoginReqDto wrongClientType = LoginReqDto.builder()
                .email(ADMIN_EMAIL)
                .password(ADMIN_PASSWORD)
                .clientType(ClientType.COMPANY)
                .build();
        LoginReqDto correct = LoginReqDto.builder()
                .email(ADMIN_EMAIL)
                .password(ADMIN_PASSWORD)
                .clientType(ClientType.ADMINISTRATOR)
                .build();
        UUID token;
        failDescription("|--->\tadmin login testing wrong details");
        token = testLogin(wrongCredentials);
        failDescription("|--->\tadmin login testing wrong Client type");
        token = testLogin(wrongClientType);
        successDescription("|--->\tadmin login testing success");
        token = testLogin(correct);
    }
    private void companyLogin() throws SQLException, CouponSystemException {
        LoginReqDto wrongCredentials = LoginReqDto.builder()
                .email(WRONG_EMAIL)
                .password(WRONG_PASSWORD)
                .clientType(ClientType.COMPANY)
                .build();
        LoginReqDto wrongClientType = LoginReqDto.builder()
                .email(COMPANY_EMAIL)
                .password(COMPANY_PASSWORD)
                .clientType(ClientType.CUSTOMER)
                .build();
        LoginReqDto correct = LoginReqDto.builder()
                .email(COMPANY_EMAIL)
                .password(COMPANY_PASSWORD)
                .clientType(ClientType.COMPANY)
                .build();
        UUID token;
        failDescription("|--->\tcompany login testing wrong details");
        token = testLogin(wrongCredentials);
        failDescription("|--->\tcompany login testing wrong Client type");
        token = testLogin(wrongClientType);
        successDescription("|--->\tcompany login testing success");
        token = testLogin(correct);
    }
    private void custmoerLogin() throws SQLException, CouponSystemException {
        LoginReqDto wrongCredentials = LoginReqDto.builder()
                .email(WRONG_EMAIL)
                .password(WRONG_PASSWORD)
                .clientType(ClientType.CUSTOMER)
                .build();
        LoginReqDto wrongClientType = LoginReqDto.builder()
                .email(CUSTOMER_EMAIL)
                .password(CUSTOMER_PASSWORD)
                .clientType(ClientType.ADMINISTRATOR)
                .build();
        LoginReqDto correct = LoginReqDto.builder()
                .email(COMPANY_EMAIL)
                .password(COMPANY_PASSWORD)
                .clientType(ClientType.CUSTOMER)
                .build();
        UUID token;
        failDescription("|--->\tcustomer login testing wrong details");
        token = testLogin(wrongCredentials);
        failDescription("|--->\tcustomer login testing wrong Client type");
        token = testLogin(wrongClientType);
        successDescription("|--->\tcustomer login testing success");
        token = testLogin(correct);
    }
    private UUID testLogin(LoginReqDto loginReqDto) throws SQLException {
        try {
            return loginManager.login(loginReqDto);
        } catch (CouponSecurityException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

