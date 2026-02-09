package com.example.bankstatement.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bankstatement.entity.Transaction;

@Service
public class TransactionParsingService {

    private final CategorizationService categorizationService;

    public TransactionParsingService(CategorizationService categorizationService) {
        this.categorizationService = categorizationService;
    }

    public List<Transaction> parseTransactions(String pdfText) {

        List<Transaction> transactions = new ArrayList<>();

        String[] lines = pdfText.split("\\r?\\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (String line : lines) {

            line = line.trim();

            // Skip header and empty lines
            if (line.isEmpty() || line.startsWith("Date")) {
                continue;
            }

            // Match lines starting with date
            if (line.matches("\\d{2}/\\d{2}/\\d{4}.*")) {
                try {
                    // 1️⃣ Extract date
                    String datePart = line.substring(0, 10);
                    LocalDate date = LocalDate.parse(datePart, formatter);

                    // 2️⃣ Remaining text
                    String remaining = line.substring(10).trim();

                    // 3️⃣ Split by space
                    String[] tokens = remaining.split(" ");

                    // 4️⃣ Amount is last token
                    String amountStr = tokens[tokens.length - 1];
                    Double amount = Double.parseDouble(amountStr);

                    // 5️⃣ Description = everything except amount
                    String description =
                            remaining.substring(0,
                                    remaining.lastIndexOf(amountStr)).trim();

                    // 6️⃣ NLP detection
                    String merchant =
                            categorizationService.detectMerchant(description);
                    String category =
                            categorizationService.detectCategory(description);

                    // 7️⃣ Build transaction
                    Transaction tx = new Transaction();
                    tx.setTransactionDate(date);
                    tx.setDescription(description);
                    tx.setAmount(amount);
                    tx.setType("DEBIT");
                    tx.setMerchant(merchant);
                    tx.setCategory(category);

                    transactions.add(tx);

                } catch (Exception e) {
                    // ignore malformed lines
                }
            }
        }

        return transactions;
    }
}
