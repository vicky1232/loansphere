package com.example.LoanSphere.Services;


import com.example.LoanSphere.Entity.LoanApplicationDetails;
import com.example.LoanSphere.Model.CommonResponse;
import com.example.LoanSphere.Model.Review;

public interface Service {


    Review reviewLoanApplications();

    CommonResponse updateApplicationStatus(Long applicationNo, String status);


//    UserDetails applicantDetail(UserDetails userDetails);
}
