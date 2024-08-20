package com.example.LoanSphere.Repository;

import com.example.LoanSphere.Entity.OtpDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<OtpDetails, Long> {

    @Modifying
    @Transactional
    @Query("Delete from OtpDetails otp where otp.emailId=:emailId")
    void deletePrevOtp(String emailId);

    @Query("select COUNT(otp) from OtpDetails otp where otp.emailId=:emailId")
    int countEmailId(String emailId);

    @Query("select e from OtpDetails e where e.emailId=:emailId and e.otpCode=:otp")
    Optional<OtpDetails> findByEmailId(String emailId, String otp);
}
