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
@ToString
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

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long expenseId;

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
    private List<Transaction> transactionList = new ArrayList<>();

    public List<Transaction> getTransactions(){
        return transactionList;
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
