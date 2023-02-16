package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TokenService {
    UUID addClient(int id, ClientType clientType);

    void clearExpiredTokens(LocalDateTime now);

    boolean isValid(UUID token, ClientType clientType);

    int getClientId(UUID token) throws CouponSecurityException;
}
