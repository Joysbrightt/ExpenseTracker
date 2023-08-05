package com.Joysbrightt.ExpenseTracker.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@Builder
public class Transaction {

    private String transactionId;

    private User userId;

    private double amount;

    private String category;

    private LocalDateTime time;

    private String description;

    private String type;

}
