package com.Joysbrightt.ExpenseTracker.exceptions;

public class BudgetNotFoundException extends RuntimeException{
    public BudgetNotFoundException(String message) {
        super(message);
    }
}
