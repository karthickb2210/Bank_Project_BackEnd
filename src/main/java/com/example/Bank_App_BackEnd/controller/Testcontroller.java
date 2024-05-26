package com.example.Bank_App_BackEnd.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testcontroller {
    @GetMapping("/user")
    public String greet(){
        return "Karthick";
    }
}
