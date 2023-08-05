package com.Joysbrightt.ExpenseTracker.data;

import com.Joysbrightt.ExpenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(User user);
    User findByUsername(String username);

}
