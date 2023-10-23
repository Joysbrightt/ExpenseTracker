package com.Joysbrightt.ExpenseTracker.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "expense")
@Builder
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ExpenseId", nullable = false)
    private Long expenseId;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime expenseDate;

    private String category;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    private List<Transaction> transaction = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "budget")
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
