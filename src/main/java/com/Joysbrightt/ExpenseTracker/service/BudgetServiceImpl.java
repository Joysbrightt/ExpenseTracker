package com.Joysbrightt.ExpenseTracker.service;

import com.Joysbrightt.ExpenseTracker.data.BudgetRepository;
import com.Joysbrightt.ExpenseTracker.exceptions.BudgetNotFoundException;
import com.Joysbrightt.ExpenseTracker.model.Budget;
import com.Joysbrightt.ExpenseTracker.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class BudgetServiceImpl implements BudgetService{

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private ExpenseService expenseService;

    public BudgetServiceImpl(BudgetRepository budgetRepository, ExpenseService expenseService) {
        this.budgetRepository = budgetRepository;
        this.expenseService = this.expenseService;
    }

  /* @Transactional annotation is applied to the method,
     creates a transactional context for the method execution.
     This means that all operations performed within the method
     will be part of a single transaction. If any of the operations fail,
    the whole transaction will be rolled back, ensuring data consistency.
  */  @Transactional
    @Override
    public Budget createBudget(Budget budget) {
      // Save the budget
      Budget savedBudget = budgetRepository.save(budget);

      // Associate expenses with the budget if needed
      List<Expense> expenses = budget.getExpenses();
      if (expenses != null & !expenses.isEmpty()){
          for (Expense expense : expenses){
              expense.setBudget(savedBudget);
              expenseService.addExpense(expense);
          }
      }
        return savedBudget;
    }

    @Override
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    @Override
    public Optional<Budget> getBudgetById(Long budgetId) {
      return Optional.ofNullable(budgetRepository.findById(budgetId)
              .orElseThrow(() -> new BudgetNotFoundException("Budget not found")));
    }

    @Override
    @Transactional
    public Budget updateBudget(Long budgetId, Budget updatedBudget) {
        Budget budget = getBudgetById(budgetId).orElseThrow(() -> new BudgetNotFoundException("Budget not found"));

        budget.setCategory(updatedBudget.getCategory());
        budget.setWeeklyPeriod(updatedBudget.getWeeklyPeriod());
        budget.setMonthlyPeriod(updatedBudget.getMonthlyPeriod());

        List<Expense> updatedExpenses = updatedBudget.getExpenses();
        List<Expense> existingExpenses = budget.getExpenses();

        if (updatedExpenses != null && !updatedExpenses.isEmpty()){
            for (int i = 0; i < updatedExpenses.size(); i++) {
                Expense updatedExpense = updatedExpenses.get(i);
                Expense existingExpense = existingExpenses.get(i);

                existingExpense.setAmount(updatedExpense.getAmount());
                existingExpense.setExpenseDate(updatedExpense.getExpenseDate());
                existingExpense.setDescription(updatedExpense.getDescription());
                expenseService.updateExpense(existingExpense.getExpenseId(), existingExpense);

            }
        }
                return budgetRepository.save(budget);
    }

    @Override
    public void deleteBudget(Long budgetId) {

      budgetRepository.delete(budgetId);
    }


}
