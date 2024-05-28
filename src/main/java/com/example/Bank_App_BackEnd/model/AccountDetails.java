package com.example.Bank_App_BackEnd.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetails {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String pan;
    @Indexed
    private String email;
    private String mobile;
    private String dob;
    private String password;
}
