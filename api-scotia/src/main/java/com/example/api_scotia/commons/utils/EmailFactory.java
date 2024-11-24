package com.example.api_scotia.commons.utils;

import com.example.api_scotia.models.response.emails.CreateLoanMessage;
import com.example.api_scotia.models.response.emails.SimulateLoanMessage;
import com.example.api_scotia.service.EmailMessage;

import java.math.BigDecimal;
import java.util.Map;

public class EmailFactory {

    public static EmailMessage createLoanMessages(String type, Map<String, Object> parameters) {
        switch (type) {
            case "loanConfirmationMessage":
                return CreateLoanMessage.builder()
                        .name(parameters.get("name").toString())
                        .lastName(parameters.get("lastName").toString())
                        .amount(new BigDecimal(parameters.get("totalAmount").toString()))
                        .installments(Integer.parseInt(parameters.get("installments").toString()))
                        .build();
            case "paymentReminder":
                return new CreateLoanMessage();
            case "simulateLoanMessage":
                return SimulateLoanMessage.builder()
                        .name(parameters.get("name").toString())
                        .dni(parameters.get("dni").toString())
                        .installments(Integer.parseInt(parameters.get("installments").toString()))
                        .amount(new BigDecimal(parameters.get("amount").toString()))
                        .monthlyPayment(new BigDecimal(parameters.get("amountQuote").toString()))
                        .email(parameters.get("email").toString())
                        .build();
        }
        throw new IllegalArgumentException("Unknown loan type " + type);
    }

    /*public static EmailMessage createRegisterAccount(String type, Map<String, Objects> parameters) {
        switch (type) {
            return "ajslkdesa";
        }
    }*/

    private EmailFactory() {}
}
