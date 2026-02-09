package com.example.bankstatement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.bankstatement.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // 🔹 Category-wise summary
    @Query("SELECT t.category, SUM(t.amount) FROM Transaction t GROUP BY t.category")
    List<Object[]> getCategorySummary();

    // 🔹 Monthly summary
    @Query("""
        SELECT MONTH(t.transactionDate), YEAR(t.transactionDate), SUM(t.amount)
        FROM Transaction t
        GROUP BY YEAR(t.transactionDate), MONTH(t.transactionDate)
    """)
    List<Object[]> getMonthlySummary();
}

