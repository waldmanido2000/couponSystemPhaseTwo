package com.jb.couponSystemPhaseTwo.config;

import com.jb.couponSystemPhaseTwo.security.LoginInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class MapConfig {
    @Bean
    public Map<UUID, LoginInfo> map() {
        return new HashMap<>();
    }
}
