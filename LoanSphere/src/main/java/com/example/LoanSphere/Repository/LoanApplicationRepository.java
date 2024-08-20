package com.example.LoanSphere.Repository;


import com.example.LoanSphere.Model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findByUser_UserId(Long userId);
}

