package com.ungspp1.gadminbackend.model.to;

import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSessionTO {
    private BigInteger id;
    private String twoFactorCode;
    private String codeStatus;
    private LocalDateTime codeGenerationDate;
    private LocalDateTime sessionGenerationDate;
}