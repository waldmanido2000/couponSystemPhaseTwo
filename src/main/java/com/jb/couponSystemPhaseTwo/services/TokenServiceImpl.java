package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.security.LoginInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final Map<UUID, LoginInfo> map;

    @Override
    public UUID addClient(int id, ClientType clientType) {
        UUID token = UUID.randomUUID();
        LoginInfo loginInfo = LoginInfo.builder()
                .id(id)
                .clientType(clientType)
                .time(LocalDateTime.now())
                .build();
        map.put(token, loginInfo);
        map.forEach((key, value) -> System.out.println("Key: " + key.toString() + ", Value: " + value.getId() + value.getClientType() + value.getTime()));
        return token;
    }

    @Override
    public void clearTokens() {
        map.clear();
    }

    @Override
    public boolean isValid(UUID token, ClientType clientType) {
        return false;
    }
}
