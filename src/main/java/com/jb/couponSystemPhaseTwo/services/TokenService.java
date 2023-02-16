package com.jb.couponSystemPhaseTwo.services;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TokenService {
    UUID addClient(int id, ClientType clientType);

    void clearTokens();

    void clearExpiredTokens(LocalDateTime now);

    boolean isValid(UUID token, ClientType clientType);
}
