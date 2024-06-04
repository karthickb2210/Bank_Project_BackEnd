package com.example.Bank_App_BackEnd.controller;


import com.example.Bank_App_BackEnd.model.AccountDetails;
import com.example.Bank_App_BackEnd.model.AuthUser;
import com.example.Bank_App_BackEnd.repository.AccountRepository;
import com.example.Bank_App_BackEnd.repository.AuthUserRepository;
import com.example.Bank_App_BackEnd.service.Loginservice;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class Registercontroller {
    private final AccountRepository accountRepository;
    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Loginservice loginservice;

//    @GetMapping("/fill-database")
//    public String fillDatabase(@RequestParam(value = "entries", defaultValue = "10") int entries) {
//        loginservice.generateRandomData(entries);
//        return entries + " entries added to the database.";
//    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody AuthUser user){
        try {
            if (userRepository.findByUsername(user.getUsername()).isPresent())
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken. Please try again");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            AuthUser save = userRepository.save(user);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
