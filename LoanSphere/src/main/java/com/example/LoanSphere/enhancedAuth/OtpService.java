package com.example.LoanSphere.enhancedAuth;

import com.example.LoanSphere.Entity.User;
import com.example.LoanSphere.Repository.UserDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private UserDetailRepo userDetailRepo;

    @Autowired
    private EmailService emailService; // Or SMS service

    public String generateAndSendOtp(User user) {
        // Generate a random 6-digit OTP
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000);

        // Save the OTP and its expiry time in the user entity
        user.setOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(10));

        // Send the OTP via email or SMS
        emailService.sendOtpEmail(user.getEmailId(), otp);
        // smsService.sendOtp(user.getMobileNo(), otp);  // Uncomment if needed

        return otp;
    }
}

