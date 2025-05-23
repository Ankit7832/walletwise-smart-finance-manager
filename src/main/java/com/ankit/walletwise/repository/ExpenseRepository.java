package com.ankit.walletwise.repository;

import com.ankit.walletwise.entity.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
    Optional<Expense> findByIdAndUserId(int id, int userId);

    List<Expense> findByUserIdOrderByDateDesc(int userId);

    List<Expense> findByUserIdAndDateBetween(int userId, LocalDate dateAfter, LocalDate dateBefore);

    List<Expense> findByUserIdAndCategory(int userId, String category);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.user.id = :userId")
    BigDecimal getTotalExpensesByUser(@Param("userId") int userId);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e " +
            "WHERE e.user.id = :userId AND e.date BETWEEN :startDate AND :endDate")
    BigDecimal getTotalExpensesByUserAndDateRange(@Param("userId") int userId,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);

    @Query("SELECT e.category, SUM(e.amount) FROM Expense e WHERE e.user.id = :userId GROUP BY e.category")
    List<Object[]> getTotalExpenseByCategory(@Param("userId") int userId);

}
