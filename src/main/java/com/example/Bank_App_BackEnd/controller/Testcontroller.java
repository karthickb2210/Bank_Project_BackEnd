package com.example.Bank_App_BackEnd.controller;


import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("http://localhost:5173")
@RestController
public class Testcontroller  {

public String findCurrentUsername() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
        String currentUserName = authentication.getName();
        return currentUserName;
    }
    return "";
}

    @GetMapping("/user")
    public String greet(){
        String name ="";
    try{
        name = findCurrentUsername();
        return name;
        }catch(Exception e){
            e.fillInStackTrace();
        }
        //System.out.println("User name: " + userDetails.getUsername());
        return name;
    }
}
