package com.example.LoanSphere.Model;

import lombok.Data;

@Data
public class ApplicationResponse {

    private CommonResponse commonResponse;
    private Long applicationNo;
    private String applicantName;
    private String applicationStatus;
}
