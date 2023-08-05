package com.Joysbrightt.ExpenseTracker.service;

import com.Joysbrightt.ExpenseTracker.dtos.CreateUserRequest;
import com.Joysbrightt.ExpenseTracker.model.User;

public interface UserService {
    User getUserById(Long userId);

    User createUser(CreateUserRequest userRequest);
}
