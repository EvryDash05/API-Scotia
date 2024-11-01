package com.example.api_scotia.models.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFinancialInfoResponse {
    private String customerFinancialInfoId;
    private String jobType;
    private BigDecimal monthlyIncome;
    private BigDecimal fixedExpenses;
    private BigDecimal excessPayment;
}
