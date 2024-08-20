package com.example.LoanSphere.Repository;
import com.example.LoanSphere.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepo extends JpaRepository<User,Long> {
    @Query("select u from User u where u.emailId=:userName")
    Optional<User> findUser(String userName);


    @Query("select e from User e where e.emailId=:emailId")
    Optional<User> findByEmailId(String emailId);
}
