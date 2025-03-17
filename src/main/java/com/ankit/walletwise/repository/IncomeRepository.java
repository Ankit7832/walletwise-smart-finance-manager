package com.ankit.walletwise.repository;

import com.ankit.walletwise.entity.Income;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income,Integer> {
    List<Income> findByUserIdOrderByDateDesc(int userId);

    List<Income> findByUserIdAndDateBetween(int userId, LocalDateTime dateAfter, LocalDateTime dateBefore);

    List<Income> findByUserIdAndSource(int userId, String category);
}
