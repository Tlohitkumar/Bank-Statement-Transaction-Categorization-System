package com.example.bankstatement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankstatement.dto.CategorySummaryResponse;
import com.example.bankstatement.dto.MonthlySummaryResponse;
import com.example.bankstatement.entity.Transaction;
import com.example.bankstatement.repository.TransactionRepository;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // 🔹 Get all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // 🔹 Category-wise summary
    @GetMapping("/summary/category")
    public List<CategorySummaryResponse> getCategorySummary() {
        return transactionRepository.getCategorySummary()
                .stream()
                .map(obj -> new CategorySummaryResponse(
                        (String) obj[0],
                        (Double) obj[1]
                ))
                .toList();
    }

    // 🔹 Monthly summary
    @GetMapping("/summary/monthly")
    public List<MonthlySummaryResponse> getMonthlySummary() {
        return transactionRepository.getMonthlySummary()
                .stream()
                .map(obj -> new MonthlySummaryResponse(
                        (Integer) obj[0], // month
                        (Integer) obj[1], // year
                        (Double) obj[2]   // total amount
                ))
                .toList();
    }
}
