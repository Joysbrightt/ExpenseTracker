package com.Joysbrightt.ExpenseTracker.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Budget {

    private String budgetId;

    private String userId;

    private String category;

    private double amount;

    private String weeklyPeriod;

    private String monthlyPeriod;

}
