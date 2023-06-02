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
@Table(name = "g10_price_history", schema = "public")
public class PriceHistoryDE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g10_id" ,length = 8, nullable = false)
    private BigInteger id; 

    @Column(name = "g10_message", length = 30)
    private String message;

    @Column (name = "g10_reference" , length = 200)
    private String reference;

    @Column(name = "g10_purchase_price" , length = 8)
    private Float newPurchasePrice;

    @Column(name = "g10_sell_price" , length = 8)
    private Float newSellPrice;

    @Column(name = "g10_base_price" , length = 8)
    private Float newBasePrice;

    @Column(name = "g10_update_date",length = 8)
    private LocalDateTime updateDate;

    @Column(name = "g10_massive_percentage" , length = 8)
    private Float massivePercentage;
}
