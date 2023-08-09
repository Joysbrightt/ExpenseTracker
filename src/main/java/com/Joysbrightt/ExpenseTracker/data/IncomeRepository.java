package com.Joysbrightt.ExpenseTracker.data;

import com.Joysbrightt.ExpenseTracker.model.Income;
import com.Joysbrightt.ExpenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    Optional<Income> getIncomeById(Long incomeId);

    void delete(Optional<Income> income);

    List<Income> findByUser(User user);
}
