package com.Joysbrightt.ExpenseTracker.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Builder
public class Dashboard {
    @Id
    private String  userId;

    private double accountBalance;

//    List<Transaction> recentTransactions;

    Map<String, Double> budgetStatus;


}
