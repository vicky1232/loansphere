package com.example.LoanSphere.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loanType;
    private Double amount;
    private LocalDate applicationDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

