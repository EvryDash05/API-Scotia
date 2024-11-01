package com.example.api_scotia.models.response;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LiquidityTransactionResponse {
    private String liquidityId;
    private LocalDate transactionDate;
    private BigDecimal amount;
    private String status;
}
