package com.ungspp1.gadminbackend.model.entity;

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
@Table(name = "g03_user", schema = "public")
public class UserDE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g03_id", length = 8, nullable = false)
    private BigInteger id;
    @Column(name = "g03_full_name", length = 50, nullable = false)
    private String fullName;
    @Column(name = "g03_document", length = 10, nullable = false)
    private String document;
    @Column(name = "g03_username", length = 15, nullable = false)
    private String username;
    @Column(name = "g03_password", length = 15, nullable = false)
    private String password;
    @Column(name = "g03_type", length = 15, nullable = false)
    private String type;
    @Column(name = "g03_branch", length = 10, nullable = true)
    private String branch;
    @Column(name = "g03_technical_level", length = 1, nullable = true)
    private String technicalLevel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "g03_contact_id", nullable = false)
    private ContactDE contactData;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "g03_address_id", nullable = false)
    private AddressDE addressData;
}
