package com.example.LoanSphere.Model;

import lombok.Data;

import java.sql.Date;

@Data
public class ApplicationReview {

    private Long applicationNo;
    private String fullName;
    private Date dateOfBirth;
    private String maritalStatus;
    private String aadhaarNo;
    private String email;
    private String mobileNo;
    private String address;
    private String employmentStatus;
    private String jobTitle;
    private double annualIncome;
    private String proofOfIncome;
    private String loanType;
    private double loanAmount;
    private String initialStatus;




}
