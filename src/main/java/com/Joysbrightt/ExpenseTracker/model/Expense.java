package com.Joysbrightt.ExpenseTracker.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Component
@Builder
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    private double amount;

    private Date time;

    private String category;

    private String description;

}
