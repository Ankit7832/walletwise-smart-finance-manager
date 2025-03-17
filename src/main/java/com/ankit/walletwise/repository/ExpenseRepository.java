package com.ankit.walletwise.repository;

import com.ankit.walletwise.entity.Expense;

import com.ankit.walletwise.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
    List<Expense> findByUserIdOrderByDateDesc(int userId);

    List<Expense> findByUserIdAndDateBetween(int userId, LocalDateTime dateAfter, LocalDateTime dateBefore);

    List<Expense> findByUserIdAndCategory(int userId, String category);

}
