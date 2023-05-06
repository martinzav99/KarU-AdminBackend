package com.ungspp1.gadminbackend.api.signup.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupUserResponseTO {
    private String username;
    private String type;
}
