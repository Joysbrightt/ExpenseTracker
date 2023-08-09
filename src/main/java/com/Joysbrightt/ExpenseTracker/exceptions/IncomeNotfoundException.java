package com.Joysbrightt.ExpenseTracker.exceptions;

import lombok.Getter;

@Getter
public class IncomeNotfoundException extends RuntimeException{
    public IncomeNotfoundException(String message) {
        super(message);
    }
}
