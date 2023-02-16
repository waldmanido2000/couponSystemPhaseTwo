package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.dto.LoginReqDto;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.services.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.UUID;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class LoginControllerImpl {
    @Autowired
    private LoginManager loginManager;

    @PostMapping("login")
    public UUID login(@RequestBody LoginReqDto loginReqDto) throws CouponSecurityException, SQLException {
        return loginManager.login(loginReqDto);
    }
}
