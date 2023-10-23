package com.Joysbrightt.ExpenseTracker.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;

import java.net.PasswordAuthentication;

@Data
@AllArgsConstructor
@Builder
@Validated
@Entity
@Table(name = "user")
@RequiredArgsConstructor

public class User {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user", nullable = false)

    private Long userId;

    private String username;

    private String email;

    @Transient
    private String password;



    public User(Long userId, String username, String email, String password) {

    }
}
