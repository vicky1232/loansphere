package com.example.LoanSphere.Controller;

import com.example.LoanSphere.Services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisteringController {

    @Autowired
    private Service service;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody com.example.LoanSphere.Model.UserDetails user) {
        try {
            com.example.LoanSphere.Model.UserDetails registeredUser = service.registerNewUser(user);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
