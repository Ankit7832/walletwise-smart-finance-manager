package com.ankit.walletwise.controller;

import com.ankit.walletwise.dto.IncomeDTO;
import com.ankit.walletwise.entity.Income;
import com.ankit.walletwise.service.IncomeService;
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
@RequestMapping("/api/incomes")
public class IncomeController {

    private final IncomeService incomeService;
    
    private final AuthenticationUtils authUtils;

    public IncomeController(IncomeService incomeService,AuthenticationUtils authUtils) {
        this.incomeService = incomeService;
        this.authUtils=authUtils;
    }

    @PostMapping
    public ResponseEntity<IncomeDTO> createIncome(@RequestBody IncomeDTO incomeDTO,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        // Get the user ID from email
        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());

        // Convert DTO to entity
        Income income = convertToEntity(incomeDTO);

        // Save income
        Income savedIncome = incomeService.createIncome(income, userId);

        // Convert entity back to DTO
        IncomeDTO responseDTO = convertToDTO(savedIncome);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeDTO> getIncomeById(@PathVariable int id,
                                                   @AuthenticationPrincipal UserDetails userDetails) {
        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        Income income = incomeService.getIncomeById(id, userId);
        return ResponseEntity.ok(convertToDTO(income));
    }

    @GetMapping
    public ResponseEntity<List<IncomeDTO>> getAllIncomes(@AuthenticationPrincipal UserDetails userDetails) {
        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        List<Income> incomes = incomeService.getAllIncomesByUser(userId);
        List<IncomeDTO> incomeDTOs = incomes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(incomeDTOs);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<IncomeDTO>> getIncomesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @AuthenticationPrincipal UserDetails userDetails) {

        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        List<Income> incomes = incomeService.getAllIncomesByUserAndDateRange(userId, startDate, endDate);
        List<IncomeDTO> incomeDTOs = incomes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(incomeDTOs);
    }

    @GetMapping("/source/{source}")
    public ResponseEntity<List<IncomeDTO>> getIncomesBySource(
            @PathVariable String source,
            @AuthenticationPrincipal UserDetails userDetails) {

        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        List<Income> incomes = incomeService.getIncomesByUserAndSource(userId, source);
        List<IncomeDTO> incomeDTOs = incomes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(incomeDTOs);
    }

    @GetMapping("/total")
    public ResponseEntity<BigDecimal> getTotalIncomes(@AuthenticationPrincipal UserDetails userDetails) {
        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        BigDecimal total = incomeService.getTotalIncomeByUser(userId);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/total/date-range")
    public ResponseEntity<BigDecimal> getTotalIncomesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @AuthenticationPrincipal UserDetails userDetails) {

        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        BigDecimal total = incomeService.getTotalIncomeByUserAndRange(userId, startDate, endDate);
        return ResponseEntity.ok(total);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeDTO> updateIncome(
            @PathVariable int id,
            @RequestBody IncomeDTO incomeDTO,
            @AuthenticationPrincipal UserDetails userDetails) {

        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        Income income = convertToEntity(incomeDTO);
        Income updatedIncome = incomeService.updateIncome(id, income, userId);
        return ResponseEntity.ok(convertToDTO(updatedIncome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(
            @PathVariable int id,
            @AuthenticationPrincipal UserDetails userDetails) {

        int userId = authUtils.getUserIdFromEmail(userDetails.getUsername());
        incomeService.deleteIncome(id, userId);
        return ResponseEntity.noContent().build();
    }

    // Helper methods
    private IncomeDTO convertToDTO(Income income) {
        return new IncomeDTO(
                income.getId(),
                income.getTitle(),
                income.getAmount(),
                income.getDescription(),
                income.getSource(),
                income.getDate()
        );
    }

    private Income convertToEntity(IncomeDTO incomeDTO) {
        Income income = new Income();
        income.setId(incomeDTO.getId());
        income.setTitle(incomeDTO.getTitle());
        income.setAmount(incomeDTO.getAmount());
        income.setDescription(incomeDTO.getDescription());
        income.setSource(incomeDTO.getSource());
        income.setDate(incomeDTO.getDate());
        return income;
    }
}