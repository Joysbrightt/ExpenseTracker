package com.Joysbrightt.ExpenseTracker.service;

import com.Joysbrightt.ExpenseTracker.model.Transaction;

public interface TransactionService {

    Transaction addTransaction(Transaction transaction);

    Transaction getTransactionById(Long transactionId, Transaction transaction);

    void deleteTransaction(Long transactionId, Long id);

    Transaction updateTransaction(Long transactionId, Transaction updatedTransaction);
}
