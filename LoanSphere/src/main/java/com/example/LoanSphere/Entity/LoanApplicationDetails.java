package com.example.LoanSphere.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "loan-application")
@Entity
@Data

public class LoanApplicationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date_of_birth")
    private java.sql.Date dateOfBirth;

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
