package com.example.LoanSphere.ServiceImpl;

import com.example.LoanSphere.Entity.User;
import com.example.LoanSphere.Repository.UserDetailRepo;
import com.example.LoanSphere.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailRepo userDetailRepo;

    public Optional<User> findUserByEmailId(String emailId) {
        return userDetailRepo.findByEmailId(emailId);
    }
}
