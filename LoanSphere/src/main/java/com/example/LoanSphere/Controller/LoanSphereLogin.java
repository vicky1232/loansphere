package com.example.LoanSphere.Controller;
import com.example.LoanSphere.JwtAuthentication.JwtHelper;
import com.example.LoanSphere.Model.JwtRequest;
import com.example.LoanSphere.Model.JwtResponse;
import com.example.LoanSphere.Model.OtpVerificationRequest;
import com.example.LoanSphere.Utility.OtpUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loansphere-service")
public class LoanSphereLogin {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;
    @Autowired
    private OtpUtility otpUtility;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody JwtRequest request) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmailId());
        this.doAuthenticate(request.getEmailId(), request.getPassword());

        String otpCode = String.valueOf(otpUtility.generateOtpCode(request.getEmailId()));
        otpUtility.sendOtpOnMail(request.getEmailId(),otpCode);

        return new ResponseEntity<>("Otp sent to your mail.", HttpStatus.OK);
    }


    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpVerificationRequest otpRequest) {

        boolean isOtpValid = otpUtility.verifyOtp(otpRequest.getEmailId(), otpRequest.getOtp());
        if (!isOtpValid) {
            return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(otpRequest.getEmailId());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .token(token)
                .emailId(userDetails.getUsername()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}