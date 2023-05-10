package com.ungspp1.gadminbackend.model.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "g05_user_reset_pass", schema = "public")
public class UserResetPassDE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g05_id", length = 8, nullable = false)
    private BigInteger id;
    @Column(name = "g05_code", length = 150, nullable = false)
    private String token;
    @Column(name = "g05_creation_date", nullable = false)
    private LocalDateTime codeGenerationDate;
    @Column(name = "g05_expired_date", nullable = false)
    private LocalDateTime codeExpirationDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "g05_user_id", nullable = false)
    private UserDE userData;
}
