package com.example.Bank_App_BackEnd.service;

import com.example.Bank_App_BackEnd.model.AuthUser;
import com.example.Bank_App_BackEnd.model.Cred;
import com.example.Bank_App_BackEnd.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class Loginservice {
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public Loginservice(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean validate(Cred cred){
        boolean ans = false;
        try {
            if(authUserRepository.findByUsername(cred.getUsername()).isPresent()){
            Optional<AuthUser> obj = authUserRepository.findByUsername(cred.getUsername());
            ans = passwordEncoder.matches(cred.getPassword(), obj.get().getPassword());
            }
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return ans;
    }
}