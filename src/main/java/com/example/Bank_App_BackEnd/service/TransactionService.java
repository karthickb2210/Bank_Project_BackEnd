package com.example.Bank_App_BackEnd.service;

import com.example.Bank_App_BackEnd.model.AccountDetails;
import com.example.Bank_App_BackEnd.model.AmountInfo;
import com.example.Bank_App_BackEnd.model.Transaction;
import com.example.Bank_App_BackEnd.repository.AccountRepository;
import com.example.Bank_App_BackEnd.repository.AmountInfoRepository;
import com.example.Bank_App_BackEnd.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private Loginservice loginservice;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AmountInfoRepository amountInfoRepository;
    public boolean saveTrans(Transaction transaction){
        transaction.setTransactionId(transactionRepository.count()+1);
        transaction.setFromAccNumber(accountRepository.findByEmail(loginservice.findCurrentUsername()).get().getAccountNumber());
        transaction.setFromName(loginservice.findCurrentUsername());
        transactionRepository.save(transaction);
        int amount = transaction.getAmount();
        Optional<AmountInfo> fromUser = amountInfoRepository.findByAccountNumber(transaction.getFromAccNumber());
        if(fromUser.isEmpty()){
            return false;
        }else{
            AmountInfo amountInfo = new AmountInfo(fromUser.get().getAccountNumber(),fromUser.get().getBalance()-amount);
            System.out.println(amountInfo.getAccountNumber());
            System.out.println(amountInfo.getBalance());
            amountInfoRepository.save(amountInfo);
        }
        Optional<AmountInfo> toUser = amountInfoRepository.findByAccountNumber(transaction.getToAccNumber());
        if(toUser.isEmpty()){
            return false;
        }else{
            AmountInfo amountInfo = new AmountInfo(toUser.get().getAccountNumber(),toUser.get().getBalance()+amount);
            System.out.println(amountInfo.getAccountNumber());
            System.out.println(amountInfo.getBalance());
            amountInfoRepository.save(amountInfo);
        }
        return true;
    }
}
