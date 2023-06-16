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
    private String codigo_unico;
    private Float monto;
    private String concepto;
    private String nombre_completo;
    private Integer documento;
}
