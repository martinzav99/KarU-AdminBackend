package com.ungspp1.gadminbackend.api.branch.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfficeResponseTO {
    private String officeCode;
    private String officeName;
}
