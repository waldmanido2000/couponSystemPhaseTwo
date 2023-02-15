package com.jb.couponSystemPhaseTwo.security;

import com.jb.couponSystemPhaseTwo.services.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginInfo {
    private int id;
    private ClientType clientType;
    private LocalDateTime time;
}
