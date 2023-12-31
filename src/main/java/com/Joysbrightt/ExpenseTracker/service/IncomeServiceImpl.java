package com.Joysbrightt.ExpenseTracker.service;

import com.Joysbrightt.ExpenseTracker.data.IncomeRepository;
import com.Joysbrightt.ExpenseTracker.data.UserRepository;
import com.Joysbrightt.ExpenseTracker.enumClass.TransactionType;
import com.Joysbrightt.ExpenseTracker.exceptions.IncomeNotfoundException;
import com.Joysbrightt.ExpenseTracker.exceptions.UserNotFoundException;
import com.Joysbrightt.ExpenseTracker.model.Income;
import com.Joysbrightt.ExpenseTracker.model.Transaction;
import com.Joysbrightt.ExpenseTracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeServiceImpl implements IncomeService{

    private final IncomeRepository incomeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository, TransactionService transactionService) {
        this.incomeRepository = incomeRepository;
        this.transactionService = transactionService;
    }


    @Autowired
    private final TransactionService transactionService;


    @Override
    public Income addIncome(Income income) {
        Income savedIncome = incomeRepository.saveAndFlush(income);
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.INCOME.getValue());
        transaction.setDescription("Income received: " + income.getDescription());
        transaction.setAmount(income.getAmount());
        transaction.setTransactionDate(income.getDate());
        transaction.setIncome(savedIncome);
        return savedIncome;
    }

    @Override
    public List<Income> getAllIncomeByUser(User user) {
        return incomeRepository.findByUser(user);
    }

    @Override
    public Optional<Income> getIncomeById(Long incomeId) {
        return Optional.ofNullable(incomeRepository.findById(incomeId)
                .orElseThrow(() -> new IncomeNotfoundException("Income not found")));
    }

    @Override
    public Income updateIncome(Long incomeId, Income updatedIncome) {
        Income incomeToUpdate = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new IncomeNotfoundException("Income not found"));

        incomeToUpdate.setDescription(updatedIncome.getDescription());
        incomeToUpdate.setAmount(updatedIncome.getAmount());
        incomeToUpdate.setDate(updatedIncome.getDate());
        return incomeRepository.saveAndFlush(incomeToUpdate);
    }

    @Override
    public boolean deleteIncome(Long incomeId) {
        Optional<Income> income = getIncomeById(incomeId);
        if (income.isPresent()) {
            incomeRepository.delete(income.get());
            return true;
        }
        return false;
    }


    @Override
    public Page<Income> getRecentIncomes(Long userId, Pageable pageable) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return (Page<Income>) incomeRepository.findRecentIncomesByUser(user, pageable);
    }

}
