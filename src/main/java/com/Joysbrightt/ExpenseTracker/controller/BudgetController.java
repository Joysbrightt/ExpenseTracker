package com.Joysbrightt.ExpenseTracker.controller;

import com.Joysbrightt.ExpenseTracker.exceptions.BudgetNotFoundException;
import com.Joysbrightt.ExpenseTracker.model.Budget;
import com.Joysbrightt.ExpenseTracker.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping("/{createBudget}")
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget){
        Budget createBudget = budgetService.createBudget(budget);
        return ResponseEntity.status(HttpStatus.CREATED).body(createBudget);
    }

    @GetMapping("/{budgetId}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long budgetId){
        Optional<Budget> budget = budgetService.getBudgetById(budgetId);
        return budget.map(ResponseEntity:: ok)
                .orElseThrow(() -> {throw new BudgetNotFoundException("Budget not found");
                });

    }

    @PutMapping("/{updateBudget")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long budgetId, Budget updatedBudget){
        Budget budget = budgetService.updateBudget(budgetId, updatedBudget);
        return ResponseEntity.ok(budget);
    }

    @GetMapping("/{deleteBudget")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long budgetId){
        budgetService.deleteBudget(budgetId);
        return ResponseEntity.noContent().build();
    }
}
