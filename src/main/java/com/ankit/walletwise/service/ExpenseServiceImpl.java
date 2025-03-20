package com.ankit.walletwise.service;

import com.ankit.walletwise.entity.Expense;
import com.ankit.walletwise.entity.User;
import com.ankit.walletwise.exception.ResourceNotFoundException;
import com.ankit.walletwise.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserService userService;


    @Override
    public Expense createExpense(Expense expense,int userId) {
        User user= userService.findUserById(userId);
        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    @Override
    public Expense getExpenseById(int id,int userId) {
        return expenseRepository
                .findByIdAndUserId(id,userId)
                .orElseThrow(()-> new ResourceNotFoundException("Expense not found or Access denied "));
    }

    @Override
    public List<Expense> getAllExpensesByUser(int userId) {
        return expenseRepository.findByUserIdOrderByDateDesc(userId);
    }

    @Override
    public List<Expense> getAllExpensesByUserAndDateRange(int userId, LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByUserIdAndDateBetween(userId,startDate,endDate);
    }

    @Override
    public List<Expense> getExpensesByUserAndCategory(int userId, String category) {
        return expenseRepository.findByUserIdAndCategory(userId,category);
    }

    @Override
    public Expense updateExpense(int id, Expense expenseDetails, int userId) {
        Expense expense= getExpenseById(id,userId);

        expense.setTitle(expenseDetails.getTitle());
        expense.setAmount(expenseDetails.getAmount());
        expense.setCategory(expenseDetails.getCategory());
        expense.setDescription(expenseDetails.getDescription());
        expense.setDate(expenseDetails.getDate());

        return expenseRepository.save(expense);
    }

    @Override
    @Transactional
    public void deleteExpense(int id, int userId) {
        Expense expense =getExpenseById(id,userId);
        expenseRepository.delete(expense);

    }

    @Override
    public BigDecimal getTotalExpenseByUser(int userId) {
        return expenseRepository.getTotalExpensesByUser(userId);
    }

    @Override
    public BigDecimal getTotalExpenseByUserAndRange(int userId, LocalDate startDate, LocalDate endDate) {
        return expenseRepository.getTotalExpensesByUserAndDateRange(userId,startDate,endDate);
    }
}
