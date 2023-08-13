package com.Joysbrightt.ExpenseTracker.data;

import com.Joysbrightt.ExpenseTracker.model.Budget;
import com.Joysbrightt.ExpenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Budget findByUser(User user);

    List<Budget> findAll();

    void delete(Long budgetId);
}
