package com.example.Bank_App_BackEnd.repository;

import com.example.Bank_App_BackEnd.model.AmountInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AmountInfoRepository extends MongoRepository<AmountInfo,Long> {
    Optional<AmountInfo> findByAccountNumber(long accountNumber);
}
