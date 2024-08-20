package com.example.LoanSphere.ServiceImpl;

import com.example.LoanSphere.Entity.LoanApplication;
import com.example.LoanSphere.Repository.LoanApplicationRepository;
import com.example.LoanSphere.Services.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    public List<LoanApplication> getLoanApplicationsByUser(Long userId) {
        return loanApplicationRepository.findByUser_UserId(userId);
    }

}

