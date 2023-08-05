package com.Joysbrightt.ExpenseTracker.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@Builder
public class UserProfile {

    private String userId;

    private String fullName;

    private byte profileImage;

    private Map<String, String > settings;
}
