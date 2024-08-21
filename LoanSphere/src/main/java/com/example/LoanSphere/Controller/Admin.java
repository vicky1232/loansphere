package com.example.LoanSphere.Controller;


import com.example.LoanSphere.Repository.LoanApplicationRepo;
import com.example.LoanSphere.Services.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class Admin {

    @Autowired
    private Service service;

    @Autowired
    private LoanApplicationRepo loanApplicationDetailsRepo;

    private final Logger logger = LoggerFactory.getLogger(Admin.class);

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

