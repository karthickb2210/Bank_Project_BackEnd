package com.example.Bank_App_BackEnd.repository;

import com.example.Bank_App_BackEnd.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction,String> {
        List<Transaction> findAllByFromAccNumber(long fromAccNumber);
}
