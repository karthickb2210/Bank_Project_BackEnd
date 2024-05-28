package com.example.Bank_App_BackEnd.controller;


import com.example.Bank_App_BackEnd.model.AccountDetails;
import com.example.Bank_App_BackEnd.model.AuthUser;
import com.example.Bank_App_BackEnd.repository.AccountRepository;
import com.example.Bank_App_BackEnd.repository.AuthUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class Registercontroller {
    private final AccountRepository accountRepository;
    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody AccountDetails accountDetails){
        try {
            if (accountRepository.findByEmail(accountDetails.getEmail()).isPresent())
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken. Please try again");
            accountDetails.setPassword(passwordEncoder.encode(accountDetails.getPassword()));
            accountRepository.save(accountDetails);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
