package com.Joysbrightt.ExpenseTracker.service;

import com.Joysbrightt.ExpenseTracker.data.ExpenseRepository;
import com.Joysbrightt.ExpenseTracker.exceptions.ExpenseException;
import com.Joysbrightt.ExpenseTracker.exceptions.TransactionNotFoundException;
import com.Joysbrightt.ExpenseTracker.model.Expense;
import com.Joysbrightt.ExpenseTracker.model.Transaction;
import com.Joysbrightt.ExpenseTracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
private ExpenseRepository expenseRepository;
    @Autowired
    public void ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    @Autowired
    private User user;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Override
    public Expense getExpenseById(Long expenseId) {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);

        if (expenseOptional.isPresent()) {
            return expenseOptional.get();
        } else {
            throw new ExpenseException("Expense not found with ID: " + expenseId);
        }
    }

    public Expense addExpense(Expense expense) {
        Transaction transaction = new Transaction();
        transaction.setAmount(expense.getAmount());
        transaction.setTransactionDate(expense.getExpenseDate().atStartOfDay());
        transaction.setExpenses(expense);

         transactionService.addTransaction(transaction);

//        return expenseRepository.save(expense);
        return expense;
    }


    @Override
    public List<Expense> getAllExpenseByUser(User user) {
      return expenseRepository.findByUser(user);
    }

    @Override
    public Transaction getTransaction(Long expenseId, Long transactionId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseException("Expense not found"));

        return expense.getTransactions().stream()
                .filter(transaction -> transaction.getId().equals(transactionId))
                .findFirst()
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found in the specified expense"));
    }

    public List<Expense> getExpenseByUser(Long userId){
        return expenseRepository.findByUser(user);
    }
}
