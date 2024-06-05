package com.example.Bank_App_BackEnd.controller;

import com.example.Bank_App_BackEnd.model.Transaction;
import com.example.Bank_App_BackEnd.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("http://localhost:5173")
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/send")
    public boolean persistTransaction(@RequestBody Transaction transaction){
        transactionService.saveTrans(transaction);
        return true;
    }
}
