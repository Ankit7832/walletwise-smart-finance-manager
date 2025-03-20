package com.ankit.walletwise.service;

import com.ankit.walletwise.entity.Income;
import com.ankit.walletwise.entity.User;
import com.ankit.walletwise.exception.ResourceNotFoundException;
import com.ankit.walletwise.repository.IncomeRepository;
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
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final UserService userService;

    @Override
    public Income createIncome(Income income, int userId) {
        User user= userService.findUserById(userId);
        income.setUser(user);
        return incomeRepository.save(income);
    }

    @Override
    public Income getIncomeById(int id, int userId) {
        return incomeRepository
                .findByIdAndUserId(id, userId)
                .orElseThrow(()-> new ResourceNotFoundException("Income doesnt exist or Access Denied"));
    }

    @Override
    public List<Income> getAllIncomesByUser(int userId) {
        return incomeRepository.findByUserIdOrderByDateDesc(userId);
    }

    @Override
    public List<Income> getAllIncomesByUserAndDateRange(int userId, LocalDate startDate, LocalDate endDate) {
        return incomeRepository.findByUserIdAndDateBetween(userId,startDate,endDate);
    }

    @Override
    public List<Income> getIncomesByUserAndSource(int userId, String source) {
        return incomeRepository.findByUserIdAndSource(userId,source);
    }

    @Override
    public Income updateIncome(int id, Income incomeDetails, int userId) {
        Income income=getIncomeById(id,userId);

        income.setTitle(incomeDetails.getTitle());
        income.setAmount(incomeDetails.getAmount());
        income.setDescription(income.getDescription());
        income.setSource(incomeDetails.getSource());
        income.setDate(incomeDetails.getDate());
        return incomeRepository.save(income);
    }

    @Override
    @Transactional
    public void deleteIncome(int id, int userId) {
        Income income=getIncomeById(id,userId);
        incomeRepository.delete(income);
    }

    @Override
    public BigDecimal getTotalIncomeByUser(int userId) {
        return incomeRepository.getTotalIncomesByUser(userId);
    }

    @Override
    public BigDecimal getTotalIncomeByUserAndRange(int userId, LocalDate startDate, LocalDate endDate) {
        return incomeRepository.getTotalIncomesByUserAndDateRange(userId,startDate,endDate);
    }
}
