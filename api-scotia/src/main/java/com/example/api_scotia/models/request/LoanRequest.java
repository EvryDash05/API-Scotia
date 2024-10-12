package com.example.api_scotia.models.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {
    private String customerId;
    private String requestType;
    private BigDecimal amount;
    private int installments;
}
