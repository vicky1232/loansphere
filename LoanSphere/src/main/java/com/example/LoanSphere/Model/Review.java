package com.example.LoanSphere.Model;

import lombok.Data;

import java.util.List;

@Data
public class Review {
private CommonResponse commonResponse;

    private List<LoanAppliactionReview> loanAppliactionReviewList;
}
