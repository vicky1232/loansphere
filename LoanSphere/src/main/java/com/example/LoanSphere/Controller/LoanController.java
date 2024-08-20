package com.example.LoanSphere.Controller;

import com.example.LoanSphere.Entity.User;
import com.example.LoanSphere.Model.LoanApplication;
import com.example.LoanSphere.ServiceImpl.LoanApplicationServiceImpl;
import com.example.LoanSphere.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanApplicationServiceImpl loanApplicationServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/status")
    public List<LoanApplication> getLoanStatus(@AuthenticationPrincipal UserDetails userDetails) {
        // Fetch the currently authenticated user using emailId
        Optional<User> user = userServiceImpl.findUserByEmailId(userDetails.getUsername());

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        // Retrieve loan applications associated with the user
        return loanApplicationServiceImpl.getLoanApplicationsByUser(user.get().getUserId());
    }
}
