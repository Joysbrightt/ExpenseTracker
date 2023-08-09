package com.Joysbrightt.ExpenseTracker.controller;

import com.Joysbrightt.ExpenseTracker.model.Income;
import com.Joysbrightt.ExpenseTracker.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RestControllerAdvice
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping("/{incomeId}")
    public ResponseEntity<Income> getIncomeById(@PathVariable Long incomeId) {
        Optional<Income> income = incomeService.getIncomeById(incomeId);
        return income.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

//        Optional<Income> serviceIncome = incomeService.getIncomeById(incomeId);
//        if (income.isPresent()){
//            return ResponseEntity.ok(income.get());
//        }
//        else {
//            return ResponseEntity.notFound().build();
//        }
    }

    @PutMapping("/{incomeId}")
    public ResponseEntity<Income> updateIncome(@PathVariable Long incomeId, @RequestBody Income updatedIncome) {
        Optional<Income> existingIncome = incomeService.getIncomeById(incomeId);
        if (existingIncome.isPresent()){
            Income savedIncome = incomeService.updateIncome(incomeId, updatedIncome);
            return ResponseEntity.ok(savedIncome);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{addIncome}")
    public ResponseEntity<Income> addIncome(@RequestBody Income income){
        Income savedIncome = incomeService.addIncome(income);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIncome);
    }

    @DeleteMapping("/{deleteIncome}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long incomeId){
        if (incomeService.deleteIncome(incomeId)){
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
