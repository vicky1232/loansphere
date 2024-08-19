package com.example.LoanSphere.Repository;

import com.example.LoanSphere.Entity.LoanDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDocumentsRepo extends JpaRepository<LoanDocuments ,Long> {

}
