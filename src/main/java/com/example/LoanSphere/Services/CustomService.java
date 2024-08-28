package com.example.LoanSphere.Services;

import com.example.LoanSphere.Entity.User;
import com.example.LoanSphere.Repository.UserDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomService implements UserDetailsService {

    @Autowired
    private UserDetailRepo userDetailRepo;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDetail= userDetailRepo.findUser(username).orElseThrow(() -> new RuntimeException("user not found"));
        return userDetail;
    }
}
