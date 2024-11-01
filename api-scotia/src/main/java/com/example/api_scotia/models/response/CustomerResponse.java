package com.example.api_scotia.models.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private String customerId;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String password;
    private CustomerFinancialInfoResponse financialInfo;
}
