package com.ankit.walletwise.controller;

import com.ankit.walletwise.dto.ExpenseDTO;
import com.ankit.walletwise.entity.Expense;
import com.ankit.walletwise.service.ExpenseService;
import com.ankit.walletwise.util.AuthenticationUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    private final AuthenticationUtils authUtils;

    public ExpenseController(ExpenseService expenseService,AuthenticationUtils authUtils) {
        this.expenseService = expenseService;
        this.authUtils=authUtils;
    }


    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO,
                                                    @AuthenticationPrincipal UserDetails userDetails) {
        // Get the user ID from email
        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());

        // Convert DTO to entity
        Expense expense = convertToEntity(expenseDTO);

        // Save expense
        Expense savedExpense = expenseService.createExpense(expense, userId);

        // Convert entity back to DTO
        ExpenseDTO responseDTO = convertToDTO(savedExpense);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable int id,
                                                     @AuthenticationPrincipal UserDetails userDetails) {
        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        Expense expense = expenseService.getExpenseById(id, userId);
        return ResponseEntity.ok(convertToDTO(expense));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses(@AuthenticationPrincipal UserDetails userDetails) {
        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        List<Expense> expenses = expenseService.getAllExpensesByUser(userId);
        List<ExpenseDTO> expenseDTOs = expenses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(expenseDTOs);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ExpenseDTO>> getExpensesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @AuthenticationPrincipal UserDetails userDetails) {

        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        List<Expense> expenses = expenseService.getAllExpensesByUserAndDateRange(userId, startDate, endDate);
        List<ExpenseDTO> expenseDTOs = expenses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(expenseDTOs);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ExpenseDTO>> getExpensesByCategory(
            @PathVariable String category,
            @AuthenticationPrincipal UserDetails userDetails) {

        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        List<Expense> expenses = expenseService.getExpensesByUserAndCategory(userId, category);
        List<ExpenseDTO> expenseDTOs = expenses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(expenseDTOs);
    }

    @GetMapping("/total")
    public ResponseEntity<BigDecimal> getTotalExpenses(@AuthenticationPrincipal UserDetails userDetails) {
        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        BigDecimal total = expenseService.getTotalExpenseByUser(userId);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/total/date-range")
    public ResponseEntity<BigDecimal> getTotalExpensesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @AuthenticationPrincipal UserDetails userDetails) {

        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        BigDecimal total = expenseService.getTotalExpenseByUserAndRange(userId, startDate, endDate);
        return ResponseEntity.ok(total);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(
            @PathVariable int id,
            @RequestBody ExpenseDTO expenseDTO,
            @AuthenticationPrincipal UserDetails userDetails) {

        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        Expense expense = convertToEntity(expenseDTO);
        Expense updatedExpense = expenseService.updateExpense(id, expense, userId);
        return ResponseEntity.ok(convertToDTO(updatedExpense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(
            @PathVariable int id,
            @AuthenticationPrincipal UserDetails userDetails) {

        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        expenseService.deleteExpense(id, userId);
        return ResponseEntity.noContent().build();
    }

    // Helper methods
    private ExpenseDTO convertToDTO(Expense expense) {
        return new ExpenseDTO(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getCategory(),
                expense.getDate()
        );
    }

    private Expense convertToEntity(ExpenseDTO expenseDTO) {
        Expense expense = new Expense();
        expense.setId(expenseDTO.getId());
        expense.setTitle(expenseDTO.getTitle());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDescription(expenseDTO.getDescription());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDate(expenseDTO.getDate());
        return expense;
    }

}