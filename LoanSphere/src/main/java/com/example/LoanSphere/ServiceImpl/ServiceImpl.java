package com.example.LoanSphere.ServiceImpl;
import com.example.LoanSphere.Entity.Role;
import com.example.LoanSphere.Entity.User;
import com.example.LoanSphere.Model.UserDetails;
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
    public UserDetails registerNewUser(UserDetails userDetails) throws Exception {
        if (userDetailRepo.findByUsername(userDetails.getUsername()).isPresent()) {
            throw new Exception("Username is already taken!");
        }
        if (userDetailRepo.findByEmailId(userDetails.getEmailId()).isPresent()) {
            throw new Exception("Email is already registered!");
        }

        // Convert UserDetails to User entity
        User user = new User();
        user.setFirstname(userDetails.getFirstname());
        user.setLastName(userDetails.getLastName());
        user.setEmailId(userDetails.getEmailId());
        user.setMobileNo(userDetails.getMobileNo());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        user.setUsername(userDetails.getUsername());

        // Assign role to user
        Role userRole = new Role();
        userRole.setRole("ROLE_USER");
        userRole.setUserMaster(user);

        //set the role to the user
        user.setRoleMaster(userRole);
        userRole.setUserMaster(user);

        // Save the user
        userDetailRepo.save(user);

        // Convert back to UserDetails for response
        userDetails.setRole(userRole.getRole());
        return userDetails;
    }

    @Override
    public String loginUser(String username, String password) throws Exception {
        User user = userDetailRepo.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Invalid password");
        }

        // Generate OTP and send it
        String generatedOtp = otpService.generateAndSendOtp(user);
        userDetailRepo.save(user);

        return generatedOtp;
    }

    @Override
    public boolean verifyOtp(String username, String otp) throws Exception {
        User user = userDetailRepo.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));
        if (user.getOtp().equals(otp) && user.getOtpExpiry().isAfter(LocalDateTime.now())) {
            return true;
        }
        return false;
    }

    @Override
    public UserDetails updateUser(String username, UserDetails updatedUser) throws Exception {
        User existingUser = userDetailRepo.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        if (updatedUser.getFirstname() != null && !updatedUser.getFirstname().isEmpty()) {
            existingUser.setFirstname(updatedUser.getFirstname());
        }
        if (updatedUser.getLastName() != null && !updatedUser.getLastName().isEmpty()) {
            existingUser.setLastName(updatedUser.getLastName());
        }
        if (updatedUser.getEmailId() != null && !updatedUser.getEmailId().isEmpty()) {
            if (!updatedUser.getEmailId().equals(existingUser.getEmailId())) {
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
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        if (updatedUser.getRole() != null) {
            Role updatedRole = new Role();
            updatedRole.setRole("ROLE_USER");
//            updatedRole.setUserMaster(updatedUser);
            existingUser.setRoleMaster(updatedRole);
            updatedRole.setUserMaster(existingUser);
        }

        userDetailRepo.save(existingUser);

        updatedUser.setRole(existingUser.getRoleMaster().getRole());
        return updatedUser;
    }
}