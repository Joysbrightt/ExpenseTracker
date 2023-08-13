package com.Joysbrightt.ExpenseTracker.controller;

import com.Joysbrightt.ExpenseTracker.dtos.LoginUser;
import com.Joysbrightt.ExpenseTracker.model.*;
import com.Joysbrightt.ExpenseTracker.service.BudgetService;
import com.Joysbrightt.ExpenseTracker.service.ExpenseService;
import com.Joysbrightt.ExpenseTracker.service.IncomeService;
import com.Joysbrightt.ExpenseTracker.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final ExpenseService expenseService;
    private final IncomeService incomeService;
    private  final BudgetService budgetService;

    private final UserService userService;
    public DashboardController(ExpenseService expenseService, IncomeService incomeService, BudgetService budgetService, UserService userService) {
        this.expenseService = expenseService;
        this.incomeService = incomeService;
        this.budgetService = budgetService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Dashboard> getDashboard(){
        User user = userService.loginUser(LoginUser.builder().build());
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page,size, Sort.by("expenseDate").descending());
        Page<Expense> recentExpensesPage = expenseService.getRecentExpenses(user, pageable);
        Page<Income> recentIncomePage = incomeService.getRecentIncomes(user.getUserId(),pageable);

        List<Income> recentIncome = recentIncomePage.getContent();
        List<Expense> recentExpenses = recentExpensesPage.getContent();
        List<Budget> budgets = budgetService.getAllBudgets();

      Dashboard dashboard = new Dashboard(recentExpenses, recentIncome, budgets);
        return ResponseEntity.ofNullable(dashboard);
    }
}
