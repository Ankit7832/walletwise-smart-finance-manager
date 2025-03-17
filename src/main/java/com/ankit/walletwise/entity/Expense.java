package com.ankit.walletwise.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private String category;
    @Column
    private String description;
    @Column(name = "expense_date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @JsonBackReference
    private User user;

    @PrePersist
    protected void onCreate(){
        this.date=LocalDateTime.now();
    }


}
