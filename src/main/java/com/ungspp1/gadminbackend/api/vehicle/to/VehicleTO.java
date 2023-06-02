package com.ungspp1.gadminbackend.api.vehicle.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleTO {
    private String plate;
    private String kilometers;
    private String dni;
    private String origin;
    private Boolean gnc;
    private String picture1;
    private String picture2;
    private String picture3;
    private ModelTO modelData;
}