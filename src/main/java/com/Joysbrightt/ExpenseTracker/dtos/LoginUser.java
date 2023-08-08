package com.Joysbrightt.ExpenseTracker.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.PasswordAuthentication;
import java.security.KeyStore;
@Data
@Builder
public class LoginUser  {

    private String username;

//    public LoginUser(char[] password) {
//        super(password);
//    }

    private PasswordAuthentication password;
}
