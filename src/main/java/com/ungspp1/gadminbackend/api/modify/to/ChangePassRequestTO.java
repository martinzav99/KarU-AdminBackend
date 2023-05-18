package com.ungspp1.gadminbackend.api.modify.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePassRequestTO {

    private String username;
    private String oldPassword;
    private String newPassword;
}