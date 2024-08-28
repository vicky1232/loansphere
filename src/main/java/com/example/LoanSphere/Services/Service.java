package com.example.LoanSphere.Services;


import com.example.LoanSphere.Entity.User;
import com.example.LoanSphere.Model.UserDetails;

public interface Service {

    UserDetails registerNewUser(UserDetails user) throws Exception;

    String loginUser(String email, String password) throws Exception;

    boolean verifyOtp(String username, String otp) throws Exception;

    UserDetails updateUser(String username,UserDetails updatedUser) throws Exception;
}
