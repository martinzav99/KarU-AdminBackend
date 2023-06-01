package com.ungspp1.gadminbackend.model.entity;


import java.math.BigInteger;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "g10_model", schema = "public")
public class PriceHistoryDE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g10_id" ,length = 8, nullable = false)
    private BigInteger id; 

    @Column (name = "g10_referencia" , length = 255)
    private String referencia;

    @Column(name = "g10_precio_compra" , length = 8)
    private Double precioCompra;

    @Column(name = "g10_precio_venta" , length = 8)
    private Double precioVenta;

    @Column(name = "g10_fecha_cambio",length = 8)
    private LocalDateTime fecha;

    @Column(name = "g10_porcentaje_masivo" , length = 8)
    private Double porcentajeMasivo;
}
