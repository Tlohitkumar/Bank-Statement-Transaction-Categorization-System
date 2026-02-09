package com.example.bankstatement.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.bankstatement.entity.Transaction;
import com.example.bankstatement.repository.TransactionRepository;
import com.example.bankstatement.service.PdfService;
import com.example.bankstatement.service.TransactionParsingService;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "*")
public class UploadController {

    private final PdfService pdfService;
    private final TransactionParsingService parsingService;
    private final TransactionRepository transactionRepository;

    public UploadController(PdfService pdfService,
                            TransactionParsingService parsingService,
                            TransactionRepository transactionRepository) {
        this.pdfService = pdfService;
        this.parsingService = parsingService;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            // 1️⃣ Extract PDF text
            String pdfText = pdfService.extractText(file);

            // 2️⃣ Parse transactions
            List<Transaction> transactions =
                    parsingService.parseTransactions(pdfText);

            // 3️⃣ Save all transactions to DB
            transactionRepository.saveAll(transactions);

            System.out.println("Saved transactions count: " + transactions.size());

            return ResponseEntity.ok(
                    "PDF processed & saved successfully. Records: " + transactions.size()
            );

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Error while processing PDF");
        }
    }
}
