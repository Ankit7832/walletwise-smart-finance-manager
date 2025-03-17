package com.ankit.walletwise.service;

import com.ankit.walletwise.entity.Income;
import com.ankit.walletwise.entity.User;
import com.ankit.walletwise.repository.IncomeRepository;
import com.ankit.walletwise.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    public Income addUserIncome(int userId,double amount,String source,String Description){
        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found "));

        Income income=new Income();
        income.setUser(user);
        income.setAmount(amount);
        income.setSource(source);
        income.setDescription(Description);

        return incomeRepository.save(income);
    }

    public List<Income> getUserIncome(int userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found"));

        List<Income> incomes=incomeRepository.findByUser(user);
        if(incomes.isEmpty()){
            log.info("No income found by id : {}",userId);
        }
        return incomes;
    }
}
