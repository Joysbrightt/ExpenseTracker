package com.Joysbrightt.ExpenseTracker.enumClass;

import lombok.Getter;

@Getter
public enum TransactionType {

    INCOME("Income"),
    EXPENSE("EXPENSE");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TransactionType fromValue(String value){
        for (TransactionType type : values()){
            if(type.value.equalsIgnoreCase(value)){
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid TransactionType value" + value);
    }
}
