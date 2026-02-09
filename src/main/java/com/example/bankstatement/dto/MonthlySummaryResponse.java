package com.example.bankstatement.dto;

public class MonthlySummaryResponse {

    private int month;
    private int year;
    private Double totalAmount;

    public MonthlySummaryResponse(int month, int year, Double totalAmount) {
        this.month = month;
        this.year = year;
        this.totalAmount = totalAmount;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
}
