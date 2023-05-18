package com.ungspp1.gadminbackend.api.vehicle.to;



import com.ungspp1.gadminbackend.api.user.to.UserTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleRequestTO {
    private String plate;
    private Float purchasePrice;
    private Float sellPrice;
    private String status;
    private String score;
    private String branch;
    private String kilometers;
    private ModelTO modelData;
    private PaperworkTO paperworkData;
}