package com.ungspp1.gadminbackend.external.adminArea.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficeTO {
    private int id;
    private String nombre;
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;
    private String codigo_postal;
    private boolean activa;
    private boolean posee_taller;
}
