package com.ankit.walletwise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    public Map<String,Object> getUserStats(int userId){
        Map<String,Object> stats=new HashMap<>();

        BigDecimal totalIncome=incomeService.getTotalIncomeByUser(userId);
        BigDecimal totalExpense=expenseService.getTotalExpenseByUser(userId);
        BigDecimal balance=totalIncome.subtract(totalExpense);

        stats.put("totalIncome",totalIncome);
        stats.put("totalExpense",totalExpense);
        stats.put("balance",balance);

        LocalDate today= LocalDate.now();
        LocalDate firstDayOfMonth= today.withDayOfMonth(1);
        LocalDate lastDayOfMonth=firstDayOfMonth.withDayOfMonth(today.lengthOfMonth());

        BigDecimal monthlyIncome=incomeService.getTotalIncomeByUserAndRange(userId,firstDayOfMonth,lastDayOfMonth);
        BigDecimal monthlyExpense=expenseService.getTotalExpenseByUserAndRange(userId,firstDayOfMonth,lastDayOfMonth);
        BigDecimal monthlyBalance=monthlyIncome.subtract(monthlyExpense);

        Map<String,Object> monthlyStats=new HashMap<>();
        monthlyStats.put("income",monthlyIncome);
        monthlyStats.put("expense",monthlyExpense);
        monthlyStats.put("balance",monthlyBalance);

        stats.put("monthly",monthlyStats);
        return stats;

    }

}
