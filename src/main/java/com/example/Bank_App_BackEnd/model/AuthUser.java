package com.example.Bank_App_BackEnd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("user")
@AllArgsConstructor
public class AuthUser {
    @Id
    private String id;
    @Indexed
    private String username;
    private String password;
    private boolean active;
}
