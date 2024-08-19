package com.example.LoanSphere.Services;


import com.example.LoanSphere.Entity.LoanApplication;
import com.example.LoanSphere.Model.ApplicationResponse;
import com.example.LoanSphere.Model.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

public interface Service {

    ApplicationResponse loanApplication(LoanApplication loanApplication);

    CommonResponse document(MultipartFile file, Long applicationNo, String documentName, String documentType);

}
