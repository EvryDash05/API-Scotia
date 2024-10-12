package com.example.api_scotia.models.response;

import com.example.api_scotia.models.request.LiquidityTransactionRequest;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardResponse {
    private String cardId;
    private String nameType;
    private Long cardNumber;
    private BigDecimal cardLimit;
    private LocalDateTime expirationDate;
    private List<LiquidityTransactionResponse>liquidityTransactionResponseList;
}
