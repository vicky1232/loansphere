package com.example.LoanSphere.Utility;

import com.example.LoanSphere.Entity.OtpDetails;
import com.example.LoanSphere.Repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
@Component
public class OtpUtility {


    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public int generateOtpCode(String emailId) {
        int count = otpRepository.countEmailId(emailId);
        int randomNo = 0;
        if (count > 0) {
            otpRepository.deletePrevOtp(emailId);
        }
        randomNo = (int) (Math.random() * 900000) + 100000;
        saveOtpInDB(emailId, String.valueOf(randomNo));
        return randomNo;
    }

    private void saveOtpInDB(String emailId, String otp){
        OtpDetails otpDetails = new OtpDetails();
        otpDetails.setOtpCode(otp);
        otpDetails.setEmailId(emailId);
        otpDetails.setExpTime(LocalDateTime.now());
        otpRepository.save(otpDetails);
    }

    @Async
    public void sendOtpOnMail(String sentTo, String otpCode) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            String msgBody = "Dear User\n Your OTP for login is: " + otpCode + " Please enter this OTP to proceed. \n Thank you,";

            System.out.println(msgBody);

            mailMessage.setFrom(sender);
            mailMessage.setTo(sentTo);
            mailMessage.setText(msgBody);
            mailMessage.setSubject("Your One-Time Password (OTP)");

            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean verifyOtp(String emailId, String otp) {
        boolean matched;

        Optional<OtpDetails> otpCode = otpRepository.findByEmailId(emailId, otp);
        OtpDetails otpManage = otpCode.get();
        Duration duration = Duration.between(otpManage.getExpTime(), LocalDateTime.now());
        long betweenTime = duration.toMinutes();
        matched = betweenTime <= 8;
        return matched;
    }
}

