package com.ankit.walletwise.repository;

import com.ankit.walletwise.entity.Expense;

import com.ankit.walletwise.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
    List<Expense> findByUser(User user);
}
