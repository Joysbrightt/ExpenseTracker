package com.Joysbrightt.ExpenseTracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException alreadyExistsException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(alreadyExistsException.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(UserNotFoundException notFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundException.getMessage());
    }

    @ExceptionHandler(ExpenseException.class)
    public ResponseEntity<String> handleExpenseException(ExpenseException expenseException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expenseException.getMessage());
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<String> handleTransactionNotFoundException(TransactionNotFoundException transactionNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(transactionNotFoundException.getMessage());
    }

    @ExceptionHandler(TransactionTypeException.class)
    public ResponseEntity<String> handleTransactionTypeException(TransactionTypeException transactionTypeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(transactionTypeException.getMessage());
    }

    @ExceptionHandler(BudgetNotFoundException.class)
    public ResponseEntity<String> handleBudgetNotFoundException(BudgetNotFoundException budgetNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(budgetNotFoundException.getMessage());
}

}
