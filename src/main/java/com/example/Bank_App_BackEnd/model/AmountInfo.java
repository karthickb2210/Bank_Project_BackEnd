package com.example.Bank_App_BackEnd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
public class AmountInfo {
    @Id
    @Indexed
    private String accountNumber;
    private int balance;
}
