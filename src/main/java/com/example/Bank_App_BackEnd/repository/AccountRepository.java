package com.example.Bank_App_BackEnd.repository;

import com.example.Bank_App_BackEnd.model.AccountDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<AccountDetails,String> {
    Optional<AccountDetails> findByEmail(String email);
}
