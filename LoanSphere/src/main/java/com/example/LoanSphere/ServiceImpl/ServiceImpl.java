package com.example.LoanSphere.ServiceImpl;
import com.example.LoanSphere.Entity.LoanApplication;
import com.example.LoanSphere.Model.CommonResponse;
import com.example.LoanSphere.Model.ApplicationReview;
import com.example.LoanSphere.Model.Review;
import com.example.LoanSphere.Repository.LoanApplicationRepo;
import com.example.LoanSphere.Services.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {


    @Autowired
    private LoanApplicationRepo loanApplicationRepo;

    @Override
    public Review reviewLoanApplications() {
        List<ApplicationReview> loanAppliactionReviews = new ArrayList<>();
        Review review = new Review();
        CommonResponse commonResponse = new CommonResponse();
        try {
            List<LoanApplication> loanApplication =loanApplicationRepo.findAll();
            if (!loanApplication.isEmpty()){
                for (LoanApplication application :loanApplication){
                    ApplicationReview loanAppliactionReview = new ApplicationReview();
                    loanAppliactionReview.setApplicationNo(application.getId());
                    loanAppliactionReview.setFullName(application.getFullName());
                    loanAppliactionReview.setAddress(application.getAddress());
                    loanAppliactionReview.setEmail(application.getEmail());
                    loanAppliactionReview.setAnnualIncome(application.getAnnualIncome());
                    loanAppliactionReview.setLoanAmount(application.getLoanAmount());
                    loanAppliactionReview.setLoanType(application.getLoanType());
                    loanAppliactionReview.setInitialStatus(application.getInitialStatus());
                    loanAppliactionReview.setJobTitle(application.getJobTitle());
                    loanAppliactionReview.setMaritalStatus(application.getMaritalStatus());
                    loanAppliactionReview.setEmploymentStatus(application.getEmploymentStatus());
                    loanAppliactionReview.setMobileNo(application.getMobileNo());
                    loanAppliactionReview.setDateOfBirth(application.getDateOfBirth());
                    loanAppliactionReview.setProofOfIncome(application.getProofOfIncome());
                    loanAppliactionReviews.add(loanAppliactionReview);
                }
                commonResponse.setCode("0000");
                commonResponse.setMsg("Applications found ");
            }else {
                commonResponse.setCode("1111");
                commonResponse.setMsg("Applications not found");
            }
        }catch (Exception e){
            commonResponse.setCode("1111");
            commonResponse.setMsg("Technical issue :" +e.getMessage());
        }
        review.setLoanAppliactionReviewList(loanAppliactionReviews);
        review.setCommonResponse(commonResponse);
        return review;
    }

    @Override
    public CommonResponse updateApplicationStatus(Long applicationNo, String status) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            LoanApplication loanApplication = loanApplicationRepo.findByAppliactionNo(applicationNo);
            if (loanApplication != null){
                loanApplicationRepo.updateStatus(applicationNo, status);
                commonResponse.setCode("0000");
                commonResponse.setMsg("Application approved successfully");
            }else {
                commonResponse.setCode("1111");
                commonResponse.setCode("Application not found ");
            }
        }catch (Exception e){
            commonResponse.setCode("1111");
            commonResponse.setMsg("Technical issue :" + e.getMessage());
        }
        return commonResponse;
    }
    }










