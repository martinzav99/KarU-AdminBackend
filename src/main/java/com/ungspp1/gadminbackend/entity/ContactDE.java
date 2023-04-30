package com.ungspp1.gadminbackend.entity;

import java.math.BigInteger;

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
@Table(name = "g02_contact", schema = "public")
public class ContactDE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g02_id", length = 8, nullable = false)
    private BigInteger id;
    @Column(name = "g02_email", length = 50, nullable = false)
    private String email;
    @Column(name = "g02_phone_code", length = 10, nullable = false)
    private String phoneCode;
    @Column(name = "g02_phone_number", length = 8, nullable = false)
    private String phoneNumber;
}
