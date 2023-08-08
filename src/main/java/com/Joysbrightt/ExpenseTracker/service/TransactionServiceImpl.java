package com.Joysbrightt.ExpenseTracker.service;

import com.Joysbrightt.ExpenseTracker.data.ExpenseRepository;
import com.Joysbrightt.ExpenseTracker.data.TransactionRepository;
import com.Joysbrightt.ExpenseTracker.exceptions.ExpenseException;
import com.Joysbrightt.ExpenseTracker.exceptions.TransactionNotFoundException;
import com.Joysbrightt.ExpenseTracker.model.Expense;
import com.Joysbrightt.ExpenseTracker.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long transactionId, Transaction transaction) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException( "Transaction not found" + transactionId + "not found"));
    }

    public void deleteTransaction(Long transactionId, Long id){
        Transaction transaction = getTransactionById(transactionId, transaction);
        transactionRepository.delete(transaction);
    }
    public void deleteTransaction(Long expenseId, Long transactionId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseException("Expense not found"));

        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));

        if (!expense.getTransactions().contains(transaction)) {
            throw new TransactionNotFoundException("Transaction not found in the specified expense");
        }

        expense.getTransactions().remove(transaction);
        transactionRepository.delete(transaction);
        expenseRepository.save(expense);
    }

    @Override
    public Transaction updateTransaction(Long transactionId, Transaction updatedTransaction) {
        Transaction transaction = getTransactionById(transactionId, transaction);
        if (updatedTransaction.getAmount() != null){
            transaction.setAmount(updatedTransaction.getAmount());
        }
        if (updatedTransaction.getDescription() != null){
            transaction.setDescription(updatedTransaction.getDescription());
        }

        if (updatedTransaction.getCategory() != null){
            transaction.setTransactionDate(updatedTransaction.getTransactionDate());
        }

        return transactionRepository.save(transaction);
    }
}