package com.Joysbrightt.ExpenseTracker.service;

import com.Joysbrightt.ExpenseTracker.data.ExpenseRepository;
import com.Joysbrightt.ExpenseTracker.data.TransactionRepository;
import com.Joysbrightt.ExpenseTracker.enumClass.TransactionType;
import com.Joysbrightt.ExpenseTracker.exceptions.ExpenseException;
import com.Joysbrightt.ExpenseTracker.exceptions.TransactionNotFoundException;
import com.Joysbrightt.ExpenseTracker.model.Expense;
import com.Joysbrightt.ExpenseTracker.model.Transaction;
import com.Joysbrightt.ExpenseTracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Expense getExpenseById(Long expenseId) {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);

        if (expenseOptional.isPresent()) {
            return expenseOptional.get();
        } else {
            throw new ExpenseException("EXPENSE not found with ID: " + expenseId);
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

    public  Transaction addExpenseTransaction(Long expenseId, Transaction transaction){
        Expense expense = getExpenseById(expenseId);

        // Set the transaction type to EXPENSE
        transaction.setType(TransactionType.EXPENSE.getValue());
        expense.getTransactions().add(transaction);
        transaction.setExpenses(expense);

        // Save the updated expense and return the saved transaction
        expenseRepository.saveAndFlush(expense);
        return transactionRepository.save(transaction);
    }


    @Override
    public List<Expense> getAllExpenseByUser(User user) {
      return expenseRepository.findByUser(user);
    }

    @Override
    public Transaction getTransaction(Long expenseId, Long transactionId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseException("EXPENSE not found"));

        return expense.getTransactions().stream()
                .filter(transaction -> transaction.getId().equals(transactionId))
                .findFirst()
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found in the specified expense"));
    }

    public List<Expense> getExpenseByUser(Long userId){
        return expenseRepository.findByUser(user);
    }

    @Override
    @Transactional
    public Expense updateExpense(Long expenseId, Expense updatedExpense) {
        Expense expense = getExpenseById(expenseId);

        expense.setAmount(updatedExpense.getAmount());
        expense.setExpenseDate(updatedExpense.getExpenseDate());
        expense.setDescription(updatedExpense.getDescription());

        // Update the transaction associated with the expense
        List<Transaction> updatedTransactions = updatedExpense.getTransactions();
        List<Transaction> existingTransactions = expense.getTransactions();


        if (updatedExpense.getTransaction() != null){
            for (int i = 0; i < updatedTransactions.size(); i++) {
                Transaction updatedTransaction = updatedTransactions.get(i);
                Transaction existingTransaction = existingTransactions.get(i);

                existingTransaction.setAmount(updatedTransaction.getAmount());
                existingTransaction.setTransactionDate(updatedTransaction.getTransactionDate());
                // Set the transaction type
                existingTransaction.setType(TransactionType.EXPENSE.getValue());

                transactionRepository.save(existingTransaction);
            }
             }

            return expenseRepository.save(expense);
    }
}
