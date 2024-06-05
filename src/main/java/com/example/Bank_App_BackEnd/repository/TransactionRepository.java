package com.example.Bank_App_BackEnd.repository;

import com.example.Bank_App_BackEnd.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction,String> {

}
