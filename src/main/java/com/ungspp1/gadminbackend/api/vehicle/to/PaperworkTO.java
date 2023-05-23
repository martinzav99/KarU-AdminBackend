package com.ungspp1.gadminbackend.api.vehicle.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaperworkTO {
    private String plate;
    private Boolean infractions;
    private Float debt;
    private Boolean vpa;
    private Boolean rva;
    private Boolean vtv;
}
