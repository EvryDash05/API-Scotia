package com.example.api_scotia.models.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponse {
    private String loanId;
    private String requestType;
    private String requestStatus;
    private BigDecimal amount;
    private LocalDateTime requestDate;
    private int installments;
}
