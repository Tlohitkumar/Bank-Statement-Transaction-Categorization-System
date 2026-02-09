package com.example.bankstatement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.bankstatement.dto.CategorySummaryResponse;
import com.example.bankstatement.dto.MonthlySummaryResponse;
import com.example.bankstatement.repository.TransactionRepository;

@RestController
@RequestMapping("/summary")
@CrossOrigin(origins = "*")
public class SummaryController {

    private final TransactionRepository transactionRepository;

    public SummaryController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/category")
    public List<CategorySummaryResponse> getCategorySummary() {
        return transactionRepository.getCategorySummary()
                .stream()
                .map(obj -> new CategorySummaryResponse(
                        (String) obj[0],
                        (Double) obj[1]
                ))
                .toList();
    }

    @GetMapping("/monthly")
    public List<MonthlySummaryResponse> getMonthlySummary() {
        return transactionRepository.getMonthlySummary()
                .stream()
                .map(obj -> new MonthlySummaryResponse(
                        (Integer) obj[0], // month
                        (Integer) obj[1], // year
                        (Double) obj[2]   // total
                ))
                .toList();
    }
}
