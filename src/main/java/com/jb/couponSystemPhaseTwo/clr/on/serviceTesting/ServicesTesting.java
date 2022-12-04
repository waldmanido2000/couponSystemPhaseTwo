package com.jb.couponSystemPhaseTwo.clr.on.serviceTesting;

import com.jb.couponSystemPhaseTwo.clr.on.AnticipatedResult;
import com.jb.couponSystemPhaseTwo.services.AdminService;
import com.jb.couponSystemPhaseTwo.services.CompanyService;
import com.jb.couponSystemPhaseTwo.services.CustomerService;
import com.jb.couponSystemPhaseTwo.utils.Debug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class ServicesTesting {
    protected static final String FAIL = AnticipatedResult.FAIL.getMessage();
    protected static final String CONTROL = AnticipatedResult.CONTROL.getMessage();
    protected static final String SUCCESS = AnticipatedResult.SUCCESS.getMessage();
    protected static final String RESET_TEXT = AnticipatedResult.RESET.getMessage();
    protected static final String FLAG_NAME = "TEST_DEBUG_MODE";

    @Autowired
    @Qualifier("companyServiceImpl")
    protected CompanyService companyService;
    @Autowired
    @Qualifier("adminServiceImpl")
    protected AdminService adminService;
    @Autowired
    @Qualifier("customerServiceImpl")
    protected CustomerService customerService;

    protected void failDescription(String description) {
        Debug.showDescription(FLAG_NAME, FAIL + description + "\t\t" + RESET_TEXT);
    }

    protected void successDescription(String description) {
        Debug.showDescription(FLAG_NAME, SUCCESS + description + "\t\t" + RESET_TEXT);
    }

    protected void controlDescription(String description) {
        Debug.showDescription(FLAG_NAME, CONTROL + description + "\t\t" + RESET_TEXT);
    }
}
