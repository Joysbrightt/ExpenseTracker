package com.Joysbrightt.ExpenseTracker.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@Builder
@Validated
public class CreateUserRequest {

    private String fullName;

    private String password;


    private String email;



}
