package com.example.api_scotia.models.request;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LiquidityTransactionRequest {
    private String cardId;
    private BigDecimal amount;
    private String status;
}