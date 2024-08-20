package com.example.LoanSphere.Services;

import com.example.LoanSphere.Entity.LoanApplication;

import java.util.List;

public interface LoanApplicationService {

    List<LoanApplication> getLoanApplicationsByUser(Long userId);


}
