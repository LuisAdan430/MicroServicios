package com.paymentchain.transaccion.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String reference; 
    private String accountlban; 
    private String date; 
    private Double amount; 
    private Double fee; 
    private String status;
    private String Channel;
}
