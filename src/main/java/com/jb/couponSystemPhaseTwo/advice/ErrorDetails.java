package com.jb.couponSystemPhaseTwo.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorDetails {
    private final String key = "couponSystemPhaseTwo";
    private String value;
}
