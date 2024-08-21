package com.example.LoanSphere.Controller;
import com.example.LoanSphere.Entity.LoanApplication;
import com.example.LoanSphere.Repository.UserDetailRepo;
import com.example.LoanSphere.ServiceImpl.LoanApplicationServiceImpl;
import com.example.LoanSphere.ServiceImpl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class User {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private LoanApplicationServiceImpl loanApplicationServiceImpl;

    @PostMapping("/message")
    public ResponseEntity<String> postMessage() {
        return ResponseEntity.ok("Message received by user successfully!");
    }

    @GetMapping("/loans-status")
    public ResponseEntity<?> getLoanStatus(@AuthenticationPrincipal UserDetails userDetails) throws RuntimeException {
        Optional<com.example.LoanSphere.Entity.User> user = userServiceImpl.findUserByEmailId(userDetails.getUsername());

        if(user.isPresent()){
            List<LoanApplication> loanApplicationsByUser = loanApplicationServiceImpl.getLoanApplicationsByUser(user.get().getUserId());
            return ResponseEntity.ok(loanApplicationsByUser);

        }

        return ResponseEntity.notFound().build();
    }
}