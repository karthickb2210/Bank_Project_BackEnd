package com.example.Bank_App_BackEnd.service;

import com.example.Bank_App_BackEnd.model.AccountDetails;
import com.example.Bank_App_BackEnd.model.AuthUser;
import com.example.Bank_App_BackEnd.model.Cred;
import com.example.Bank_App_BackEnd.repository.AccountRepository;
import com.example.Bank_App_BackEnd.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class Loginservice {
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;
    public Loginservice(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

//    private final Faker faker = new Faker();
//
//    public void generateRandomData(int count) {
//        List<AccountDetails> persons = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            AccountDetails person = new AccountDetails();
//            person.setEmail(faker.internet().emailAddress());
//            person.setFirstName(faker.name().fullName());
//            person.setPassword(faker.internet().password());
////            person.setAge(faker.number().numberBetween(18, 80));
////            person.setAddress(faker.address().fullAddress());
//            persons.add(person);
//        }
//        accountRepository.saveAll(persons);
//    }

    public boolean validate(Cred cred){

        //cred
        // username: karthickb2210@gmail.com
        // password: Karthick@123
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
