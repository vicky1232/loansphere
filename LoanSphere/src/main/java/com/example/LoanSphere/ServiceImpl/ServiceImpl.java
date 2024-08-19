package com.example.LoanSphere.ServiceImpl;
import com.example.LoanSphere.Entity.LoanApplicationDetails;
import com.example.LoanSphere.Model.CommonResponse;
import com.example.LoanSphere.Model.LoanAppliactionReview;
import com.example.LoanSphere.Model.Review;
import com.example.LoanSphere.Repository.LoanApplicationDetailsRepo;
import com.example.LoanSphere.Repository.UserDetailRepo;
import com.example.LoanSphere.Services.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {


    @Autowired
    private LoanApplicationDetailsRepo loanApplicationDetailsRepo;

    @Override
    public Review reviewLoanApplications() {
        List<LoanAppliactionReview> loanAppliactionReviews = new ArrayList<>();
        Review review = new Review();
        CommonResponse commonResponse = new CommonResponse();
        try {
            List<LoanApplicationDetails> loanApplication = loanApplicationDetailsRepo.findAll();
            if (!loanApplication.isEmpty()){
                for (LoanApplicationDetails application :loanApplication){
                    LoanAppliactionReview loanAppliactionReview = new LoanAppliactionReview();
                    loanAppliactionReview.setApplicationNo(application.getApplicationId());
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
            LoanApplicationDetails loanApplicationDetails = loanApplicationDetailsRepo.findByAppliactionNo(applicationNo);
            if (loanApplicationDetails != null){
                loanApplicationDetailsRepo.updateStatus(applicationNo, status);
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










