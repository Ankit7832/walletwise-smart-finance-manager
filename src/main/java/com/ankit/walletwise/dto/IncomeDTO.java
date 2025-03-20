package com.ankit.walletwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDTO {
    private int id;
    private String title;
    private BigDecimal amount;
    private String description;
    private String source;
    private LocalDate date;
}
