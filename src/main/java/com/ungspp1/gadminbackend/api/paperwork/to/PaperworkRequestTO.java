package com.ungspp1.gadminbackend.api.paperwork.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaperworkRequestTO {
    
    private String plate;
    private Boolean title;
    private Boolean cedula; 
    private Boolean cuit;
    private Boolean historical;
    private Boolean infractions;
    private Float debt;
    private Boolean vpa;
    private Boolean rva;
    private Boolean vtv;
}
