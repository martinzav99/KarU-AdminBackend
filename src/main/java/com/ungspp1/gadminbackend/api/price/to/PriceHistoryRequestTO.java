package com.ungspp1.gadminbackend.api.price.to;

import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceHistoryRequestTO {
    private String referencia;
    private Double precioCompra;
    private Double precioVenta;
    private LocalDateTime fechaCambio;
    private Double porcentajeMasivo;
}
