package com.example.LoanSphere.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "loan_application")
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_application_id")
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "aadhaar_no")
    private String aadhaarNo;
    @Column(name = "marital_status")
    private String maritalStatus;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile_no")
    private String mobileNo;
    @Column(name = "address")
    private String address;
    @Column(name = "employment_status")
    private String employmentStatus;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "annual_income")
    private double annualIncome;
    @Column(name = "proof_of_income")
    private String proofOfIncome;
    @Column(name = "loan_type")
    private String loanType;
    @Column(name = "loan_amount")
    private double loanAmount;
    @Column(name = "initial_status")
    private String initialStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
        private User user;




}
