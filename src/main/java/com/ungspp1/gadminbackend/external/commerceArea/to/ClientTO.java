package com.ungspp1.gadminbackend.external.commerceArea.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientTO {
    
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String numTelefono;
    private String fecha;
}
