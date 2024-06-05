package com.example.Bank_App_BackEnd.service;

import com.example.Bank_App_BackEnd.model.*;
import com.example.Bank_App_BackEnd.repository.AccountRepository;
import com.example.Bank_App_BackEnd.repository.AmountInfoRepository;
import com.example.Bank_App_BackEnd.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private AmountInfoRepository amountInfoRepository;

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

    public String findCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        return "";
    }

    public UserInfo findDetails(UserInfo user){
        Optional<AccountDetails> acc = accountRepository.findByEmail(findCurrentUsername());
        long accnumber = 0;
        if(acc.isPresent()) {
            user.setName(acc.get().getFirstName());
            accnumber = acc.get().getAccountNumber();
        }
        Optional<AmountInfo> balance = amountInfoRepository.findByAccountNumber(accnumber);
        user.setBalance(balance.get().getBalance());
        return user;
    }


}
