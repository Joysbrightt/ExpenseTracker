package com.Joysbrightt.ExpenseTracker.service;

import com.Joysbrightt.ExpenseTracker.data.UserRepository;
import com.Joysbrightt.ExpenseTracker.dtos.CreateUserRequest;
import com.Joysbrightt.ExpenseTracker.dtos.LoginUser;
import com.Joysbrightt.ExpenseTracker.exceptions.UserAlreadyExistsException;
import com.Joysbrightt.ExpenseTracker.exceptions.UserNotFoundException;
import com.Joysbrightt.ExpenseTracker.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            return user.get();
        }
        else {
            throw new UserNotFoundException("user with ID" + userId + "not found");
        }

    }

    @Override
    public User createUser(CreateUserRequest userRequest) {
        if (userRepository.findByUsername(userRequest.getFullName()) != null){
            throw new UserAlreadyExistsException("Username already exist");
        }
        User user = User.builder()
                .email(userRequest.getEmail())
                .password(userRequest.getEmail())
                .username(userRequest.getFullName())
                .build();
        return userRepository.save(user);
    }

    @Override
    public User loginUser(LoginUser loginUser) {

        if (userRepository.findByUsername(loginUser.getUsername()) == null){
            throw new UserNotFoundException("Username could not be found");
        }
        User user = User.builder()
                .email(loginUser.getUsername())
                .password(String.valueOf(loginUser.getPassword()))
                .build();
        return user;
    }


}
