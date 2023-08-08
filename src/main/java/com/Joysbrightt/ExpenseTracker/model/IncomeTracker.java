package com.Joysbrightt.ExpenseTracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder

public class IncomeTracker {

    private String incomeId;

    private User userId;

    private double amount;

    private String source;

    private LocalDateTime date;




}
