package com.jb.couponSystemPhaseTwo.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private final String key = "couponSystemPhaseTwo";
    private String value;
}
