package com.ankit.walletwise.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name" , nullable = false)
    private String name;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "registration_date",updatable = false)
    private LocalDateTime registrationDate;
    @PrePersist
    protected void onCreate(){
        this.registrationDate=LocalDateTime.now();
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<Expense> expenses=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<Income> incomes=new ArrayList<>();

}
