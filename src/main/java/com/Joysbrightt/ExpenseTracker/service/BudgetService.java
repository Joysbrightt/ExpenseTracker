package com.Joysbrightt.ExpenseTracker.service;

import com.Joysbrightt.ExpenseTracker.model.Budget;

import java.util.List;
import java.util.Optional;

public interface BudgetService {

    Budget createBudget(Budget budget);

    List<Budget> getAllBudgets();

    Optional<Budget> getBudgetById(Long budgetId);

    Budget updateBudget(Long budgetId, Budget updatedBudget);

    void deleteBudget(Long budgetId);
}
