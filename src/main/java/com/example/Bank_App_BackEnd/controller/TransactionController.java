package com.example.Bank_App_BackEnd.controller;

import com.example.Bank_App_BackEnd.entity.Transaction;
import com.example.Bank_App_BackEnd.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/getAllTransaction")
    public List<Transaction> getTransactions(){
        return transactionService.getTrans();
    }
    
    @GetMapping("/transaction/{id}")
    public List<Transaction> getUserTrans(@PathVariable long id){
        return transactionService.getUserTransaction(id);
    }
}
