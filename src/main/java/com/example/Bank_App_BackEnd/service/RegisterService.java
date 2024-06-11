package com.example.Bank_App_BackEnd.service;

import com.example.Bank_App_BackEnd.entity.AccountDetails;
import com.example.Bank_App_BackEnd.entity.AmountInfo;
import com.example.Bank_App_BackEnd.entity.AuthUser;
import com.example.Bank_App_BackEnd.repository.AccountRepository;
import com.example.Bank_App_BackEnd.repository.AmountInfoRepository;
import com.example.Bank_App_BackEnd.repository.AuthUserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class RegisterService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AmountInfoRepository amountInfoRepository;

    @Autowired
    private AuthUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegisterService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> addAccountService(AccountDetails accountDetails) {
        try {
            long random = getRandom();
            accountDetails.setUserId(accountDetails.getDob());
            accountDetails.setAccountNumber(random);
            accountRepository.save(accountDetails);
            amountInfoRepository.save(new AmountInfo(random, 10000));
            log.info("Adding user...");
            AuthUser user = AuthUser.builder().id(accountDetails.getDob())
                    .username(accountDetails.getEmail())
                    .password(passwordEncoder.encode(accountDetails.getPassword()))
                    .active(true).build();
            userRepository.save(user);
            return ResponseEntity.ok().body("Created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error");
        }

    }

    public long getRandom(){
        return (long) (Math.random()*100000000);
    }
}
