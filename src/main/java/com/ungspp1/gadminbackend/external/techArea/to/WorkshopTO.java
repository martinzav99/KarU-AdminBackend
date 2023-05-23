package com.ungspp1.gadminbackend.external.techArea.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopTO {
    private int id_taller;
    private String nombre;
    private String direccion;
    private String localidad;
    private String provincia;
    private String cod_postal;
    private String mail;
    private String telefono;
    private int capacidad;
    private int cant_tecnicos;
}
