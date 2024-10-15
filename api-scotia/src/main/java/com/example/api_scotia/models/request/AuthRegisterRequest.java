package com.example.api_scotia.models.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRegisterRequest {
    private String name;
    private String lastName;
    private String numberPhone;
    private String address;
    private String email;
    private String password;
    private CustomerFinancialInfoRequest financialInfoRequest;
}
