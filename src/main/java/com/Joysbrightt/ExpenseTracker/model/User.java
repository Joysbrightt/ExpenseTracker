package com.Joysbrightt.ExpenseTracker.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@Builder
@Validated
@Entity
@Table(name = "\"user\"")
@RequiredArgsConstructor

public class User {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)

    private Long userId;

    private String username;

    private String email;

    private String password;

    public User(Long userId, String username, String email, String password) {

    }
}
