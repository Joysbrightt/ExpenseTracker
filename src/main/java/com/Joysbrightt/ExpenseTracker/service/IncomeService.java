package com.Joysbrightt.ExpenseTracker.service;

import com.Joysbrightt.ExpenseTracker.model.Income;
import com.Joysbrightt.ExpenseTracker.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IncomeService {

    Income addIncome(Income income);

    List<Income> getAllIncomeByUser(User user);

    Optional<Income> getIncomeById(Long incomeId);

    Income updateIncome(Long incomeId, Income updatedIncome);

    boolean deleteIncome(Long incomeId);

    List<Income> getRecentIncomes(User user, Pageable limit);

}
