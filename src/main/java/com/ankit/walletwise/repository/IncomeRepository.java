package com.ankit.walletwise.repository;

import com.ankit.walletwise.entity.Income;
import com.ankit.walletwise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income,Integer> {
    List<Income> findByUser(User user);
}
