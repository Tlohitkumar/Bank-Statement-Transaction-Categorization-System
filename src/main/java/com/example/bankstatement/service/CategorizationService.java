package com.example.bankstatement.service;

import org.springframework.stereotype.Service;

@Service
public class CategorizationService {

    public String detectMerchant(String description) {

        String desc = description.toLowerCase();

        if (desc.contains("amazon")) return "Amazon";
        if (desc.contains("swiggy")) return "Swiggy";
        if (desc.contains("zomato")) return "Zomato";
        if (desc.contains("uber")) return "Uber";
        if (desc.contains("ola")) return "Ola";
        if (desc.contains("flipkart")) return "Flipkart";

        return "Unknown";
    }

    public String detectCategory(String description) {

        String desc = description.toLowerCase();

        if (desc.contains("amazon") || desc.contains("flipkart"))
            return "SHOPPING";

        if (desc.contains("swiggy") || desc.contains("zomato"))
            return "FOOD";

        if (desc.contains("uber") || desc.contains("ola"))
            return "TRAVEL";

        if (desc.contains("electricity") || desc.contains("bill"))
            return "BILLS";

        return "OTHERS";
    }
}

