package com.example.LoanSphere.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "loan_document")
public class LoanDocuments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;

//    @Column(name = "loan_application_id")
//    private Long loanApplicationId;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_data")
    private String documentData;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "loan_application_id")
    private LoanApplication loanApplication;
}
