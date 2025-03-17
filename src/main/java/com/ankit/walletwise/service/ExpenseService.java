package com.ankit.walletwise.service;

import com.ankit.walletwise.entity.Expense;
import com.ankit.walletwise.entity.User;
import com.ankit.walletwise.repository.ExpenseRepository;
import com.ankit.walletwise.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public Expense addUserExpense(int userId,double amount,String category,String Description){
        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found"));

        Expense expense= new Expense();
        expense.setUser(user);
        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setDescription(Description);

        return expenseRepository.save(expense);
    }
    public List<Expense> getUserExpense(int userId){
        User user =userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found"));

        List<Expense> expense=expenseRepository.findByUser(user);
        if(expense.isEmpty()){
            log.info("No expense found by Id : {}",userId);
        }
        return expense;
    }
}
