package com.example.Bank_App_BackEnd.controller;


import com.example.Bank_App_BackEnd.model.UserInfo;
import com.example.Bank_App_BackEnd.service.Loginservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:5173")
@RestController
public class Testcontroller  {
    @Autowired
    private Loginservice loginservice;

    @GetMapping("/user")
    public UserInfo greet(){
        UserInfo user = new UserInfo();
        return loginservice.findDetails(user);
    }

}
