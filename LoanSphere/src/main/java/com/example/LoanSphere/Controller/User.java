package com.example.LoanSphere.Controller;
import com.example.LoanSphere.Entity.LoanApplication;
import com.example.LoanSphere.Services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class User {

    @Autowired
    private Service service;
    @PostMapping("/message")
    public ResponseEntity<String> postMessage() {
        return ResponseEntity.ok("Message received by user successfully!");
    }


    @PostMapping("/loan-application")
    public ResponseEntity<?> applyForLoan(@RequestBody LoanApplication loanApplication){
        return ResponseEntity.ok(service.loanApplication(loanApplication));
    }

    @PostMapping("/document-upload")
    public ResponseEntity<?> uploadDocuments(@RequestParam("file") MultipartFile file, @RequestParam("applicationNo") Long applicationNo, @RequestParam("documentName")String documentName, @RequestParam("documentType")String documentType){
        return ResponseEntity.ok(service.document(file, applicationNo, documentName, documentType));
    }
}