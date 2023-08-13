package com.Joysbrightt.ExpenseTracker.service;

import com.Joysbrightt.ExpenseTracker.model.Expense;
import com.Joysbrightt.ExpenseTracker.model.Transaction;
import com.Joysbrightt.ExpenseTracker.model.User;

import java.util.List;

public interface ExpenseService {

 Expense addExpense(Expense expense);
 Expense getExpenseById(Long expenseId);

 Expense updateExpense(Long expenseId, Expense updatedExpense);

 List<Expense> getExpenseByUser(Long userId);

 List<Expense> getAllExpenseByUser(User user);

 Transaction getTransaction(Long expenseId, Long transactionId);

 Transaction addExpenseTransaction(Long expenseId, Transaction transaction);

}
