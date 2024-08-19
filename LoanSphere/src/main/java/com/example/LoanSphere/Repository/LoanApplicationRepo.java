package com.example.LoanSphere.Repository;

import com.example.LoanSphere.Entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LoanApplicationRepo extends JpaRepository<LoanApplication, Long> {

    @Query("select d from LoanApplication d where d.id =:applicationNo")
    LoanApplication findByAppliactionNo(Long applicationNo);

}
