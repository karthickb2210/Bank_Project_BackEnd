package com.example.Bank_App_BackEnd.controller;

import com.example.Bank_App_BackEnd.model.Cred;
import com.example.Bank_App_BackEnd.service.Loginservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("http://localhost:5173")
@RestController
public class Logincontroller {

    @Autowired
    private Loginservice loginservice;

    @PostMapping("/validate")
    public boolean validate(@RequestBody Cred cred){
        return loginservice.validate(cred);
    }
}
