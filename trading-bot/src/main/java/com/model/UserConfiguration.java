package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;



@Data
@Entity
@Table(name = "UserConfiguration")
public class UserConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Double lossPercent;

    @Column
    private Double profitPercent;

    @Column
    private Double quantityPerOrder;

    @ManyToOne
    @JoinColumn(name = "user_id") // Foreign key column name
    private User user;
}
  

