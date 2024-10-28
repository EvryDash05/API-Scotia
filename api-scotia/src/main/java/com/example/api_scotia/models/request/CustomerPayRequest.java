package com.example.api_scotia.models.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPayRequest {
    private String status;
    private String amount;
    @Builder.Default
    private LocalDate paymentDate = LocalDate.now();
}
