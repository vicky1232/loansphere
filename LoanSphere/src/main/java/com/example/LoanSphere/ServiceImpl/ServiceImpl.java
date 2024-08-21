package com.example.LoanSphere.ServiceImpl;
import com.example.LoanSphere.Entity.Role;
import com.example.LoanSphere.Entity.User;
import com.example.LoanSphere.Repository.RoleRepository;
import com.example.LoanSphere.Repository.UserDetailRepo;
import com.example.LoanSphere.Services.Service;
import com.example.LoanSphere.enhancedAuth.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    @Autowired
    private UserDetailRepo userDetailRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OtpService otpService;

    @Override
    public User registerNewUser(User user) throws Exception {
        // Check if username or email is already taken
        if (userDetailRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("Username is already taken!");
        }
        if (userDetailRepo.findByEmailId(user.getEmailId()).isPresent()) {
            throw new Exception("Email is already registered!");
        }

        // Encode the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign role to user
        Role userRole = roleRepository.findByRole("ROLE_USER")
                .orElseThrow(() -> new Exception("Role not found."));

        // Set the user in the role and vice versa
        userRole.setUserMaster(user);
        user.setRoleMaster(userRole);

        // Save the user (this will also save the role due to CascadeType.ALL)
        return userDetailRepo.save(user);
    }

    @Override
    public String loginUser(String username, String password) throws Exception {
        User user = userDetailRepo.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Invalid password");
        }

        //String userEmailId = String.valueOf(userDetailRepo.findByUsername3(username));
        // Generate OTP and send it (email/SMS service)
        String generatedOtp = otpService.generateAndSendOtp(user);
        userDetailRepo.save(user);

        // Here, you would send the OTP to the user via email/SMS
        // emailService.sendOtp(user.getEmailId(), generatedOtp);

        return generatedOtp;
    }

    @Override
    public boolean verifyOtp(String username, String otp) throws Exception {
        User user = userDetailRepo.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        if (user.getOtp().equals(otp) && user.getOtpExpiry().isAfter(LocalDateTime.now())) {
            // OTP is correct and has not expired
            return true;
        }
        return false;
    }

    @Override
    public User updateUser(String username,User updatedUser) throws Exception {
        // Fetch the existing user from the database
        User existingUser = userDetailRepo.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        // Update user details with the values from updatedUser if they are not null
        if (updatedUser.getFirstname() != null && !updatedUser.getFirstname().isEmpty()) {
            existingUser.setFirstname(updatedUser.getFirstname());
        }

        if (updatedUser.getLastName() != null && !updatedUser.getLastName().isEmpty()) {
            existingUser.setLastName(updatedUser.getLastName());
        }

        if (updatedUser.getEmailId() != null && !updatedUser.getEmailId().isEmpty()) {
            if (!updatedUser.getEmailId().equals(existingUser.getEmailId())) {
                // Check if the new email is already taken by another user
                if (userDetailRepo.findByEmailId(updatedUser.getEmailId()).isPresent()) {
                    throw new Exception("Email is already registered!");
                }
                existingUser.setEmailId(updatedUser.getEmailId());
            }
        }

        if (updatedUser.getMobileNo() != null && !updatedUser.getMobileNo().isEmpty()) {
            existingUser.setMobileNo(updatedUser.getMobileNo());
        }

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            // Encode the new password before updating it
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        // Check if there's a need to update the user's role
        if (updatedUser.getRoleMaster() != null && updatedUser.getRoleMaster().getRole() != null) {
            Role updatedRole = roleRepository.findByRole(updatedUser.getRoleMaster().getRole())
                    .orElseThrow(() -> new Exception("Role not found"));
            existingUser.setRoleMaster(updatedRole);
            updatedRole.setUserMaster(existingUser);  // Set the user reference in the role entity
        }

        // Save the updated user details
        return userDetailRepo.save(existingUser);
    }

}