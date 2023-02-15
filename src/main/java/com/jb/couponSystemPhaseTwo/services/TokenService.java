package com.jb.couponSystemPhaseTwo.services;

import java.util.UUID;

public interface TokenService {
    UUID addClient(int id, ClientType clientType);

    void clearTokens();

    boolean isValid(UUID token, ClientType clientType);
}
