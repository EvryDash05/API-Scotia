package com.example.api_scotia.models.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPayResponse {
    private String id;
    private String status;
    private String amount;
    private LocalDate paymentDate;
}
