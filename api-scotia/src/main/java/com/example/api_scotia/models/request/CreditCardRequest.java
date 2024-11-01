package com.example.api_scotia.models.request;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardRequest {
    private String clientId;
    private String nameType;
    private Long cardNumber;
    private BigDecimal cardLimit;
    private LocalDateTime expirationDate;
    private String cardType;
}

