package com.example.LoanSphere.Controller;


import com.example.LoanSphere.Model.UserDetails;
import com.example.LoanSphere.Services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private Service service;

    @PostMapping("/message")
    public ResponseEntity<String> postMessage() {
        return ResponseEntity.ok("Message received by user successfully!");
    }


    @PostMapping("/loginUser")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        try {
            String loginDetails = service.loginUser(username, password);
            return new ResponseEntity<>(loginDetails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/verify-OTP")
    public ResponseEntity<?> verifyOtp(@RequestParam String username, @RequestParam String otp) {
        try {
            boolean isVerified = service.verifyOtp(username, otp);
            if (isVerified) {
                return new ResponseEntity<>("OTP verified", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updating-USER")
    public ResponseEntity<?> updateUser(@RequestParam String username, @RequestBody UserDetails updatedUser) {
        try {
            UserDetails user = service.updateUser(username, updatedUser);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}