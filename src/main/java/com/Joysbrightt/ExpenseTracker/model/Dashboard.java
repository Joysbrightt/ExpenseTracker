package com.Joysbrightt.ExpenseTracker.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userDashboardId;

    private User user;

    List<Expense> recentExpenses;

    private List<Income> recentIncomes;

    private List<Budget> budgets;

    public Dashboard(List<Expense> recentExpenses, List<Income> recentIncome, List<Budget> budgets) {
    }

    public Map<String, BigDecimal> getBudgetStatus() {
        return budgetStatus;
    }

    Map<String, BigDecimal> budgetStatus;


    public Dashboard(Long userDashboardId, User user, List<Expense> recentExpenses, List<Income> recentIncomes, List<Budget> budgets, Map<String, BigDecimal> budgetStatus) {
        this.userDashboardId = userDashboardId;
        this.user = user;
        this.recentExpenses = recentExpenses;
        this.recentIncomes = recentIncomes;
        this.budgets = budgets;
        this.budgetStatus = budgetStatus;
    }
}
