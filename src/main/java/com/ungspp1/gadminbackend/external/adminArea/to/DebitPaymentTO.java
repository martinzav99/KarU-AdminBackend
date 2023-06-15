package com.ungspp1.gadminbackend.external.adminArea.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebitPaymentTO {
    private String code;
    private Float amount;
    private String concept;
    private String fullName;
    private String dni;
}
