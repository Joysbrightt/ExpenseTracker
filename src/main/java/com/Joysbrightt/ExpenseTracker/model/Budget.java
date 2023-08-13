package com.Joysbrightt.ExpenseTracker.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder

public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String budgetId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String category;

    private BigDecimal amount;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private List<Expense> expenses = new ArrayList<>();

    private String weeklyPeriod;

    private String monthlyPeriod;

}
