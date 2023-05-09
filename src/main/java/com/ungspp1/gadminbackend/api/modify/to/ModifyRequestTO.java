package com.ungspp1.gadminbackend.api.modify.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModifyRequestTO { 
    private String username;
    private String password;
    private String email;
}
