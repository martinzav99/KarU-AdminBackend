package com.ungspp1.gadminbackend.api.login.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseTO {
    private String sessionStatus;
    private String type;
}
