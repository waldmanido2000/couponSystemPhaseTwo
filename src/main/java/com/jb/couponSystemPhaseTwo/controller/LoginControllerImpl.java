package com.jb.couponSystemPhaseTwo.controller;

import com.jb.couponSystemPhaseTwo.dto.LoginReqDto;
import com.jb.couponSystemPhaseTwo.dto.LoginResDto;
import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.services.ClientService;
import com.jb.couponSystemPhaseTwo.services.ClientType;
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
    public LoginResDto login(@RequestBody LoginReqDto loginReqDto) throws CouponSecurityException, SQLException {
        UUID token = UUID.randomUUID();
        String email = loginReqDto.getEmail();
        String password = loginReqDto.getPassword();
        ClientType clientType = loginReqDto.getClientType();
        ClientService clientService = loginManager.login(email, password, clientType);
        System.out.println(clientService);
        return LoginResDto.builder()
                .token(token)
                .clientType(clientType)
                .build();
    }
}
