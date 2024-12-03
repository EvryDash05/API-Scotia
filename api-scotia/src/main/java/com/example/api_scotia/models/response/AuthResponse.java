package com.example.api_scotia.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String customerId;
    private String username;
    private String lastName;
    private String email;
    private String phone;
    private String message;
    private String token;
    private List<String> roles;
    private boolean status;
}
