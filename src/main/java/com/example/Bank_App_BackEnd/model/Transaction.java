package com.example.Bank_App_BackEnd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
public class Transaction {
    @Id
    @Indexed
    private Long transactionId;
    private Long fromAccNumber;
    private Long toAccNumber;
    private String fromName;
    private String toName;
    private int amount;
    private boolean done;
}
