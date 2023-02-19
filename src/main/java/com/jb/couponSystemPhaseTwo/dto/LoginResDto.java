package com.jb.couponSystemPhaseTwo.dto;

import com.jb.couponSystemPhaseTwo.services.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResDto {
    private UUID token;
    private ClientType clientType;
    private int id;
}
