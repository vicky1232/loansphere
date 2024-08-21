package com.example.LoanSphere.Repository;

import com.example.LoanSphere.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>
{

    Optional<Role> findByRole(String role);
}
