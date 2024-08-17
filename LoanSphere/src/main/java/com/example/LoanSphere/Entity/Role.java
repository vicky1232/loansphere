package com.example.LoanSphere.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long role_id;
    @Column(name = "role")
    private String role;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User userMaster;
}
