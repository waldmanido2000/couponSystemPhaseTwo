package com.jb.couponSystemPhaseTwo.services;

import com.jb.couponSystemPhaseTwo.exceptions.CouponSecurityException;
import com.jb.couponSystemPhaseTwo.exceptions.SecurityMessage;
import com.jb.couponSystemPhaseTwo.security.LoginInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final Map<UUID, LoginInfo> map;

    @Override
    public UUID addClient(int id, ClientType clientType) {
        LoginInfo newLoginInfo = LoginInfo.builder()
                .id(id)
                .clientType(clientType)
                .time(LocalDateTime.now())
                .build();
        UUID existingKey = null;

        // Look for an existing LoginInfo with the same ID
        for (UUID key : map.keySet()) {
            LoginInfo loginInfo = map.get(key);
            if (loginInfo.getId() == id) {
                // Found an existing LoginInfo with the same ID
                existingKey = key;
                break;
            }
        }

        if (existingKey != null) {
            // Remove the existing LoginInfo with the same ID
            map.remove(existingKey);
        }

        // Add the new LoginInfo to the map
        UUID newKey = UUID.randomUUID();
        map.put(newKey, newLoginInfo);

        map.forEach((key, value) -> System.out.println("Key: " + key.toString() + ", Value: " + value.getId() + value.getClientType() + value.getTime()));

        return newKey;
    }

    @Override
    public void clearTokens() {
        map.clear();
    }

    @Override
    public void clearExpiredTokens(LocalDateTime now) {
        System.out.println(now);
        List<UUID> keysToRemove = new ArrayList<>();
        for (Map.Entry<UUID, LoginInfo> entry : map.entrySet()) {
            LoginInfo value = entry.getValue();
            if (Duration.between(value.getTime(), now).toSeconds() > 60) {
                keysToRemove.add(entry.getKey());
            }
        }
        keysToRemove.forEach(key -> map.remove(key));
        map.forEach((key, value) -> System.out.println("Key: " + key.toString() + ", Value: " + value.getTime()));
    }
    public UUID getToken(LoginInfo loginInfo) throws CouponSecurityException {
        // Look for an existing LoginInfo with the same ID
        for (UUID key : map.keySet()) {
            LoginInfo existingLoginInfo = map.get(key);
            if (existingLoginInfo.getId() == loginInfo.getId()) {
                // Found an existing LoginInfo with the same ID
                return key;
            }
        }
        throw new CouponSecurityException(SecurityMessage.LOGIN_FAIL);
    }


    @Override
    public boolean isValid(UUID token, ClientType clientType) {
        return false;
    }
}
