package com.ankit.walletwise.controller;

import com.ankit.walletwise.entity.Expense;
import com.ankit.walletwise.entity.Income;
import com.ankit.walletwise.service.ExpenseService;
import com.ankit.walletwise.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/income")
@RequiredArgsConstructor
public class IncomeController {
    private final IncomeService incomeService;

    @PostMapping("/add")
    public ResponseEntity<Income> addUserIncome(@RequestParam int userId,
                                                 @RequestParam double amount,
                                                 @RequestParam String source,
                                                 @RequestParam(required = false) String note){
        Income income= incomeService.addUserIncome(userId,amount,source,note);
        return ResponseEntity.ok(income);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<Income>> getUserIncome(@PathVariable int userId){
        List<Income> incomes=incomeService.getUserIncome(userId);
        return ResponseEntity.ok(incomes);
    }
}
