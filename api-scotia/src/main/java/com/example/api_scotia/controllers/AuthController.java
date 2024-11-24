package com.example.api_scotia.controllers;

import com.example.api_scotia.business.UserDetailsBusiness;
import com.example.api_scotia.models.request.AuthCreateUserRequest;
import com.example.api_scotia.models.request.AuthLoginRequest;
import com.example.api_scotia.models.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserDetailsBusiness userDetailsBusiness;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginRequest request) {
        return ResponseEntity.ok(this.userDetailsBusiness.loginUser(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthCreateUserRequest request) {
        return ResponseEntity.ok(this.userDetailsBusiness.createUser(request));
    }

    /*@GetMapping("/find/{customerId}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable String customerId) {
        return ResponseEntity.ok(this.userDetailsBusiness.findCustomerById(customerId));
    }*/

    /*@DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String username) {
        this.userBusiness.deleteCustomerByUsername(username);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }*/

}
