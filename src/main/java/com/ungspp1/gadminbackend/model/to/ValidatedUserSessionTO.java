package com.ungspp1.gadminbackend.model.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidatedUserSessionTO {
    String username;
    String type;
    Boolean validation;
}
