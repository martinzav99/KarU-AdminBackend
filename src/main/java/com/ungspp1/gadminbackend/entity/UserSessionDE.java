package com.ungspp1.gadminbackend.entity;

import java.math.BigInteger;
import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "g04_user_session", schema = "public")
public class UserSessionDE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g04_id", length = 8, nullable = false)
    private BigInteger id;
    @Column(name = "g04_tf_code", length = 5, nullable = false)
    private String twoFactorCode;
    @Column(name = "g04_code_status", length = 15, nullable = false)
    private String codeStatus;
    @Column(name = "g04_code_date", nullable = false)
    private Date codeGenerationDate;
    @Column(name = "g04_session_date", nullable = false)
    private Date sessionGenerationDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "g04_user_id", nullable = false)
    private UserDE userData;
}
