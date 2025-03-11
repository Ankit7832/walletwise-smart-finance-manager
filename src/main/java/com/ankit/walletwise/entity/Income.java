package com.ankit.walletwise.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "incomes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private String source;
    @Column
    private String note;
    @Column(name = "income_date", nullable = false ,updatable = false)
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
