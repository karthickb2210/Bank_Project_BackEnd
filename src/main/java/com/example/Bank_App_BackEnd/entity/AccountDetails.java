package com.example.Bank_App_BackEnd.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDetails {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String pan;
    @Indexed
    private long accountNumber;
    private String email;
    private String mobile;
    private String dob;
    private String password;
}
