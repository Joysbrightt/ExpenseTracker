package com.Joysbrightt.ExpenseTracker.data;

import com.Joysbrightt.ExpenseTracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findById(Long transactionId);
}
