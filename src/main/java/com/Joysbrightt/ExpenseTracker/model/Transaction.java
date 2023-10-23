package com.Joysbrightt.ExpenseTracker.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "transaction")
@AllArgsConstructor
public class Transaction {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "transactionId", nullable = false)

        private Long transactionId;

//    private User userId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "expense")
    @ToString.Exclude
    private Expense expense;

    @Column(nullable = false)
    private BigDecimal amount;

    private String category;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    private String description;

    private String type;

    @ManyToOne
    @JoinColumn(name = "income")
    private Income income;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Transaction that = (Transaction) o;
        return getTransactionId() != null && Objects.equals(getTransactionId(), that.getTransactionId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Long getId(){
        return transactionId;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }
}
