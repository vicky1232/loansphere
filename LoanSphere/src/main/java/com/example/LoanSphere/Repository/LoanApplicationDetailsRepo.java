package com.example.LoanSphere.Repository;

import com.example.LoanSphere.Entity.LoanApplicationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface LoanApplicationDetailsRepo extends JpaRepository<LoanApplicationDetails,Long> {
      @Query("select d from LoanApplicationDetails d where d.applicationId =:applicationNo")
      LoanApplicationDetails findByAppliactionNo(Long applicationNo);
@Transactional
@Modifying
      @Query("update LoanApplicationDetails d set d.initialStatus=:status where d.applicationId=:applicationNo")
      void updateStatus(Long applicationNo, String status);
}
