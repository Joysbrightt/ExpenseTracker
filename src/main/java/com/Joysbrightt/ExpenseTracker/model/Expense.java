package com.Joysbrightt.ExpenseTracker.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "expenses")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long expenseId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate expenseDate;

    private String category;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "expenses", cascade = CascadeType.ALL)
    private List<Transaction> transaction = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "budget_id")
    private Budget budget;

    public List<Transaction> getTransactions(){
        return transaction;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Expense expense = (Expense) o;
        return getExpenseId() != null && Objects.equals(getExpenseId(), expense.getExpenseId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }



}
