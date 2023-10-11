package com.Joysbrightt.ExpenseTracker.model;

import lombok.*;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
public class UserProfile extends User {

    private User user;

//    private String fullName;

    private byte profileImage;

    private Map<String, String > settings;

    public UserProfile(Long userId, String username, String email, String password) {
        super(userId, username, email, password);
    }
}
