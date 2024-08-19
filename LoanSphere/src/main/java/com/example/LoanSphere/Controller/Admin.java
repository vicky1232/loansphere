package com.example.LoanSphere.Controller;


import com.example.LoanSphere.Repository.LoanApplicationDetailsRepo;
import com.example.LoanSphere.Services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class Admin {

    @Autowired
    private Service service;

    @Autowired
    private LoanApplicationDetailsRepo loanApplicationDetailsRepo;

    @PostMapping("/message")
    public ResponseEntity<String> postMessage() {
        return ResponseEntity.ok("Message received by admin successfully!");

    }
    @GetMapping("/get-loan-application")
    public ResponseEntity<?> getApplication(){
        return ResponseEntity.ok(service.reviewLoanApplications());
    }
    @PutMapping("/approve-reject")
    public ResponseEntity<?> approveOrRejectApplication(@RequestParam(name = "applicationNo")Long applicationNo, @RequestParam(name = "status")String status){
        return ResponseEntity.ok(service.updateApplicationStatus(applicationNo, status));
    }
}

