package com.Joysbrightt.ExpenseTracker.service;

import com.Joysbrightt.ExpenseTracker.model.Expense;
import com.Joysbrightt.ExpenseTracker.model.User;

import java.util.List;

public interface ExpenseService {

 Expense addExpense(Expense expense);

 List<Expense> getAllExpenseByUser(User user);

}
