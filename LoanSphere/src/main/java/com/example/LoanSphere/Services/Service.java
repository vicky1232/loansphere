package com.example.LoanSphere.Services;


import com.example.LoanSphere.Entity.User;

public interface Service {

    User registerNewUser(User user) throws Exception;

    String loginUser(String email, String password) throws Exception;

    boolean verifyOtp(String username, String otp) throws Exception;

    User updateUser(String username,User updatedUser) throws Exception;
}
