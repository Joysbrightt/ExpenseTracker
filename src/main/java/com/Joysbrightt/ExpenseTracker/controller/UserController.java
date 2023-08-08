package com.Joysbrightt.ExpenseTracker.controller;

import com.Joysbrightt.ExpenseTracker.dtos.CreateUserRequest;
import com.Joysbrightt.ExpenseTracker.dtos.LoginUser;
import com.Joysbrightt.ExpenseTracker.model.User;
import com.Joysbrightt.ExpenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody CreateUserRequest request){
        User user = userService.createUser(request);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUser loginUser){
        User user = userService.loginUser(loginUser);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }
}
