package com.example.LoanSphere.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "otp_details")
@Data
public class OtpDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "otp_id")
    private Long otpId;
    @Column(name = "otp_code")
    private String otpCode;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "exp_time")
    private LocalDateTime expTime;
}

