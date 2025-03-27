package com.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "users") // Definindo um nome explícito para a tabela
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true) // Garantindo unicidade para login
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String binanceApiKey;

    @Column(nullable = false)
    private String binanceSecretKey;

    @Column
    private Double saldoInicio;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserConfiguration> configurations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTrackingTicker> trackingTickers;

    
}