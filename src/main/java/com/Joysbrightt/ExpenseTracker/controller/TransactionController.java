package com.Joysbrightt.ExpenseTracker.controller;

import com.Joysbrightt.ExpenseTracker.exceptions.ExpenseException;
import com.Joysbrightt.ExpenseTracker.exceptions.TransactionNotFoundException;
import com.Joysbrightt.ExpenseTracker.model.Expense;
import com.Joysbrightt.ExpenseTracker.model.Transaction;
import com.Joysbrightt.ExpenseTracker.model.User;
import com.Joysbrightt.ExpenseTracker.service.ExpenseService;
import com.Joysbrightt.ExpenseTracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TransactionController {

@Autowired
    private ExpenseService expenseService;

@Autowired
    private TransactionService transactionService;

@GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(Long userId){
    List<Expense> expenseList = expenseService.getAllExpenseByUser(User.builder().userId(userId).build());
    return new ResponseEntity<>(expenseList, HttpStatus.OK);
}

    @PostMapping("/{expenseId/{transactions}")
    public ResponseEntity<Transaction> addTransaction(@PathVariable Long expenseId, @RequestBody Transaction transaction){
    Transaction addedTransaction = transactionService.getTransactionById(expenseId, transaction);
   return new ResponseEntity<>(addedTransaction, HttpStatus.CREATED);
    }

    @DeleteMapping("/{expenseId}/transactions/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long expenseId, @PathVariable Long transactionId) {
        try {
            transactionService.deleteTransaction(expenseId, transactionId);
            return new ResponseEntity<>("Transaction deleted successfully", HttpStatus.OK);
        } catch (TransactionNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ExpenseException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting the transaction", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
