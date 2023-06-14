package com.ungspp1.gadminbackend.api.vehicle.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnableVehicleTO {
    
    private String plate;
    private String photo1;
    private String photo2;
    private String photo3;
    private String branch;
}
