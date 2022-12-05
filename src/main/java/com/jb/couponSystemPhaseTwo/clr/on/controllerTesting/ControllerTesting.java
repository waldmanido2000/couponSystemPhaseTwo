package com.jb.couponSystemPhaseTwo.clr.on.controllerTesting;

import com.jb.couponSystemPhaseTwo.clr.on.AnticipatedResult;
import com.jb.couponSystemPhaseTwo.utils.Debug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public abstract class ControllerTesting {
    protected static final String FAIL = AnticipatedResult.FAIL.getMessage();
    protected static final String CONTROL = AnticipatedResult.CONTROL.getMessage();
    protected static final String SUCCESS = AnticipatedResult.SUCCESS.getMessage();
    protected static final String RESET_TEXT = AnticipatedResult.RESET.getMessage();
    protected static final String FLAG_NAME = "TEST_DEBUG_MODE";

    @Autowired
    protected RestTemplate restTemplate;

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
