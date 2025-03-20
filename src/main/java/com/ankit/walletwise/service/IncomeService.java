package com.ankit.walletwise.service;

import com.ankit.walletwise.entity.Income;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IncomeService {
    Income createIncome(Income income, int userId);
    Income getIncomeById(int id,int userId);
    List<Income> getAllIncomesByUser(int userId);
    List<Income> getAllIncomesByUserAndDateRange(int userId, LocalDate startDate, LocalDate endDate);
    List<Income> getIncomesByUserAndSource(int userId, String source);
    Income updateIncome(int id,Income incomeDetails,int userId);
    void deleteIncome (int id,int userId);
    BigDecimal getTotalIncomeByUser(int userId);
    BigDecimal getTotalIncomeByUserAndRange(int userId,LocalDate startDate,LocalDate endDate);
}
