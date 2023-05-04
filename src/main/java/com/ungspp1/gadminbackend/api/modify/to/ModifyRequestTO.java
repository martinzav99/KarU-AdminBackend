package com.ungspp1.gadminbackend.api.modify.to;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModifyRequestTO {
    
    private BigInteger id;     
    private String username;
    private String password;
    private String email;
}
