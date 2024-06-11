package com.example.Bank_App_BackEnd.controller;

import com.example.Bank_App_BackEnd.entity.AccountDetails;
import com.example.Bank_App_BackEnd.repository.AccountRepository;
import com.example.Bank_App_BackEnd.repository.AmountInfoRepository;
import com.example.Bank_App_BackEnd.repository.AuthUserRepository;
import com.example.Bank_App_BackEnd.service.Loginservice;
import com.example.Bank_App_BackEnd.service.RegisterService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
@Log4j2
public class Registercontroller {

    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private final AuthUserRepository userRepository;

    @Autowired
    private final Loginservice loginservice;

    @Autowired
    private final AmountInfoRepository amountInfoRepository;

    @Autowired
    private RegisterService registerService;

//    @GetMapping("/fill-database")
//    public String fillDatabase(@RequestParam(value = "entries", defaultValue = "10") int entries) {
//        loginservice.generateRandomData(entries);
//        return entries + " entries added to the database.";
//    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AccountDetails accountDetails){
        return registerService.addAccountService(accountDetails);
    }

}
