package com.Joysbrightt.ExpenseTracker.service;

import com.Joysbrightt.ExpenseTracker.model.Expense;
import com.Joysbrightt.ExpenseTracker.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService{


    @Override
    public Expense addExpense(Expense expense) {
//        return expenseRepository.save(expense);
        return null;
    }

    @Override
    public List<Expense> getAllExpenseByUser(User user) {
//        return expenseRepository.findByUser(user);
    return null;
    }
}
