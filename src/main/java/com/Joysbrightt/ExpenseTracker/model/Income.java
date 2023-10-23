package com.Joysbrightt.ExpenseTracker.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "income")
@AllArgsConstructor
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "incomeId", nullable = false)
    private Long incomeId;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    private BigDecimal amount;

    private String description;

    private LocalDateTime date;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Income income = (Income) o;
        return getIncomeId() != null && Objects.equals(getIncomeId(), income.getIncomeId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
