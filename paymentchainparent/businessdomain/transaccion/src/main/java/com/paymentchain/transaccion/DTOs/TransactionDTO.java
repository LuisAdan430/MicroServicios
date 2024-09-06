package com.paymentchain.transaccion.DTOs;

import lombok.Data;

@Data
public class TransactionDTO {
    private String reference;
    private String accountlban;
    private String fecha;
    private Double amount;
    private Double fee;
    private Integer channel;
    //private Integer status;
}
