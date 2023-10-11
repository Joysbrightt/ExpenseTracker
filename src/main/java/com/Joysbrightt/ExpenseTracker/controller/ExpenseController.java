package com.Joysbrightt.ExpenseTracker.controller;

import com.Joysbrightt.ExpenseTracker.exceptions.ExpenseException;
import com.Joysbrightt.ExpenseTracker.exceptions.TransactionTypeException;
import com.Joysbrightt.ExpenseTracker.model.Expense;
import com.Joysbrightt.ExpenseTracker.model.Transaction;
import com.Joysbrightt.ExpenseTracker.model.User;
import com.Joysbrightt.ExpenseTracker.service.ExpenseService;
import com.Joysbrightt.ExpenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addExpense(@RequestBody Expense expense){
        expenseService.addExpense(expense);
        return ResponseEntity.ok("EXPENSE added successfully");
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getExpenseByUser(@PathVariable Long userId){
        User user = userService.getUserById(userId);
        List<Expense> expenseList = expenseService.getAllExpenseByUser(user);
        return ResponseEntity.ok(expenseList);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expense>> getExpenseByUserId(@PathVariable Long userId){
        List<Expense> expense = expenseService.getExpenseByUser(userId);
        return ResponseEntity.ok(expense);
    }

    @PostMapping("/{expenseId}/transactions")
    public ResponseEntity<Transaction> addExpenseTransaction(@PathVariable Long expenseId, @RequestBody Transaction transaction){

        try{
            Transaction addedTransaction = expenseService.addExpenseTransaction(expenseId, transaction);
        return ResponseEntity.ok((addedTransaction));
        } catch (ExpenseException | TransactionTypeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PutMapping("/{expenseId]")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long expenseId, @RequestBody Expense updateExpense){
        Expense expense = expenseService.updateExpense(expenseId, updateExpense);

        return ResponseEntity.ok(expense);
    }
}
