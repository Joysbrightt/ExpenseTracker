package com.Joysbrightt.ExpenseTracker.controller;

import com.Joysbrightt.ExpenseTracker.model.Expense;
import com.Joysbrightt.ExpenseTracker.model.User;
import com.Joysbrightt.ExpenseTracker.service.ExpenseService;
import com.Joysbrightt.ExpenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addExpense(@RequestBody Expense expense){
        expenseService.addExpense(expense);
        return ResponseEntity.ok("Expense added successfully");
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getExpenseByUser(@PathVariable Long userId){
        User user = userService.getUserById(userId);
        List<Expense> expenseList = expenseService.getAllExpenseByUser(user);
        return ResponseEntity.ok(expenseList);
    }
}
