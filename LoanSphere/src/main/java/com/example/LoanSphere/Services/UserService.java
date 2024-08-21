package com.example.LoanSphere.Services;

import com.example.LoanSphere.Entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmailId(String emailId);
}
