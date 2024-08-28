package com.example.LoanSphere.Model;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class JwtResponse {
    private String token;
    private String emailId;
}
