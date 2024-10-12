package com.example.api_scotia.models.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFinancialInfoRequest {
    private String clientId;
    private String jobType;
    private BigDecimal monthlyIncome;
    private BigDecimal fixedExpenses;
    private BigDecimal excessPayment;
}
