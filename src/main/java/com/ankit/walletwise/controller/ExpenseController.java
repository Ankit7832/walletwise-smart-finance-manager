package com.ankit.walletwise.controller;

import com.ankit.walletwise.entity.Expense;
import com.ankit.walletwise.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity<Expense> addUserExpense(@RequestParam int userId,
                                              @RequestParam double amount,
                                              @RequestParam String category,
                                              @RequestParam(required = false) String note){
        Expense expense= expenseService.addUserExpense(userId,amount,category,note);
        return ResponseEntity.ok(expense);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<Expense>> getUserExpense(@PathVariable int userId){
        List<Expense> expenses=expenseService.getUserExpense(userId);
        return ResponseEntity.ok(expenses);
    }
}
