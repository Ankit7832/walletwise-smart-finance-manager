package com.ankit.walletwise.repository;

import com.ankit.walletwise.entity.Income;
import com.ankit.walletwise.entity.Income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income,Integer> {
    Optional<Income> findByIdAndUserId(int id, int userId);
    
    List<Income> findByUserIdOrderByDateDesc(int userId);

    List<Income> findByUserIdAndDateBetween(int userId, LocalDate dateAfter, LocalDate dateBefore);

    List<Income> findByUserIdAndSource(int userId, String source);

    @Query("SELECT COALESCE(SUM(i.amount), 0) FROM Income i WHERE i.user.id = :userId")
    BigDecimal getTotalIncomesByUser(@Param("userId") int userId);

    @Query("SELECT COALESCE(SUM(i.amount), 0) FROM Income i " +
            "WHERE i.user.id = :userId AND i.date BETWEEN :startDate AND :endDate")
    BigDecimal getTotalIncomesByUserAndDateRange(@Param("userId") int userId,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);

    List<Income> findByUserId(int userId);
}
