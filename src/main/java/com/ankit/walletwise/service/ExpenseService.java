package com.ankit.walletwise.service;

import com.ankit.walletwise.entity.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ExpenseService {
    Expense createExpense(Expense expense,int userId);
    Expense getExpenseById(int id,int userId);
    List<Expense> getAllExpensesByUser(int userId);
    List<Expense> getAllExpensesByUserAndDateRange(int userId, LocalDate startDate,LocalDate endDate);
    List<Expense> getExpensesByUserAndCategory(int userId, String category);
    Expense updateExpense(int id,Expense expenseDetails,int userId);
    void deleteExpense (int id,int userId);
    BigDecimal getTotalExpenseByUser(int userId);
    BigDecimal getTotalExpenseByUserAndRange(int userId,LocalDate startDate,LocalDate endDate);
    Map<String, BigDecimal> getTotalExpenseByCategory(int userId);

}
