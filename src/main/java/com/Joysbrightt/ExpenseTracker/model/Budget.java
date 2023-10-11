package com.Joysbrightt.ExpenseTracker.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "Budget")

@AllArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long budgetId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String category;

    private BigDecimal amount;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Expense> expenses;

    private String weeklyPeriod;

    private String monthlyPeriod;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Budget budget = (Budget) o;
        return getBudgetId() != null && Objects.equals(getBudgetId(), budget.getBudgetId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
