package com.example.Bank_App_BackEnd.service;


import com.example.Bank_App_BackEnd.entity.AccountDetails;
import com.example.Bank_App_BackEnd.entity.AmountInfo;
import com.example.Bank_App_BackEnd.entity.Transaction;
import com.example.Bank_App_BackEnd.repository.AccountRepository;
import com.example.Bank_App_BackEnd.repository.AmountInfoRepository;
import com.example.Bank_App_BackEnd.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void saveTrans(Transaction transaction){
        transaction.setTransactionId(transactionRepository.count()+1);
        Optional<AccountDetails> accountDetails = accountRepository.findByEmail(loginservice.findCurrentUsername());
        accountDetails.ifPresent(details -> transaction.setFromAccNumber(details.getAccountNumber()));
        transaction.setFromName(loginservice.findCurrentUsername());
        transactionRepository.save(transaction);
        int amount = transaction.getAmount();
        Optional<AmountInfo> fromUser = amountInfoRepository.findByAccountNumber(transaction.getFromAccNumber());
        if(fromUser.isEmpty()){
            return;
        }else{
            AmountInfo amountInfo = new AmountInfo(fromUser.get().getAccountNumber(),fromUser.get().getBalance()-amount);
            amountInfoRepository.save(amountInfo);
        }
        Optional<AmountInfo> toUser = amountInfoRepository.findByAccountNumber(transaction.getToAccNumber());
        if (toUser.isPresent()) {
            AmountInfo amountInfo = new AmountInfo(toUser.get().getAccountNumber(),toUser.get().getBalance()+amount);
           // System.out.println(amountInfo.getAccountNumber());
            // System.out.println(amountInfo.getBalance());
            amountInfoRepository.save(amountInfo);
        }
    }

    public List<Transaction> getTrans() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getUserTransaction(long id) {
        return transactionRepository.findAllByFromAccNumber(id);
    }
}
