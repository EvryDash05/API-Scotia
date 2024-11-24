package com.example.api_scotia.models.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulateLoanRequest {
    private String name;
    private String dni;
    private BigDecimal amount;
    private Integer installments;
    private String email;
}
