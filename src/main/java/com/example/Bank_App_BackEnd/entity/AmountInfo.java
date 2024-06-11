package com.example.Bank_App_BackEnd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
public class AmountInfo {
    @Id
    @Indexed
    private long accountNumber;
    private int balance;
}
