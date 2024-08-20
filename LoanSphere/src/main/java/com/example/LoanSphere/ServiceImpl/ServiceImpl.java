package com.example.LoanSphere.ServiceImpl;
import com.example.LoanSphere.Entity.LoanApplication;
import com.example.LoanSphere.Entity.LoanDocuments;
import com.example.LoanSphere.Model.ApplicationResponse;
import com.example.LoanSphere.Model.CommonResponse;
import com.example.LoanSphere.Repository.LoanApplicationRepo;
import com.example.LoanSphere.Repository.LoanDocumentsRepo;
import com.example.LoanSphere.Services.Service;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.Base64;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    @Autowired
    private LoanApplicationRepo loanApplicationRepo;
    @Autowired
    private LoanDocumentsRepo loanDocumentsRepo;

    @Override
    public ApplicationResponse loanApplication(LoanApplication loanApplication) {

        CommonResponse commonResponse = new CommonResponse();
        ApplicationResponse applicationResponse = new ApplicationResponse();
        try {
            loanApplication.validate();
            loanApplication.setInitialStatus("PENDING");
            LoanApplication savedLoanApplication = loanApplicationRepo.save(loanApplication);

            applicationResponse.setApplicationNo(savedLoanApplication.getId());
            applicationResponse.setApplicantName(savedLoanApplication.getFullName());
            applicationResponse.setApplicationStatus(savedLoanApplication.getInitialStatus());
            commonResponse.setCode("0000");
            commonResponse.setMsg("Application submitted successfully");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            commonResponse.setCode("1111");
            commonResponse.setMsg("Exception: " + e.getMessage());
        }
        applicationResponse.setCommonResponse(commonResponse);
        return applicationResponse;
    }

    @Override
    public CommonResponse document(MultipartFile file, Long applicationNo, String documentName, String documentType) {
        CommonResponse commonResponse = new CommonResponse();
        LoanDocuments loanDocuments = new LoanDocuments();
        try {
            LoanApplication loanApplication = loanApplicationRepo.findByAppliactionNo(applicationNo);
            if (loanApplication.getInitialStatus().equalsIgnoreCase("APPROVED")) {
                String fileName = loanApplication.getFullName() + " " + loanApplication.getId() + " " + documentName;
                String filePath = "D:/ShubhamApplication/loansphere/LoanSphere/target" + fileName;

                byte[] fileBytes = IOUtils.toByteArray(file.getInputStream());
                String base64EncodedFile = Base64.getEncoder().encodeToString(fileBytes);
                FileOutputStream fos = new FileOutputStream(filePath);
                fos.write(base64EncodedFile.getBytes());

                loanDocuments.setDocumentName(documentName);
                loanDocuments.setDocumentType(documentType);
                loanDocuments.setLoanApplication(loanApplication);
                loanDocuments.setDocumentData(filePath);

                loanDocumentsRepo.save(loanDocuments);
                commonResponse.setCode("0000");
                commonResponse.setMsg("Document upload successfully");
            }else {
                commonResponse.setCode("1111");
                commonResponse.setMsg("Your loan application is not approved");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            commonResponse.setCode("1111");
            commonResponse.setMsg("Exception :" +e.getMessage());
        }
        return commonResponse;
    }
}