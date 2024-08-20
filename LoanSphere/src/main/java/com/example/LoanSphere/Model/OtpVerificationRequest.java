package com.example.LoanSphere.Model;

import lombok.Data;

@Data
public class OtpVerificationRequest {
    private String emailId;
    private String otp;
}
