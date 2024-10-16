package com.example.api_scotia.controllers;

import com.example.api_scotia.business.UserBusiness;
import com.example.api_scotia.models.request.AuthRegisterRequest;
import com.example.api_scotia.models.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final UserBusiness userBusiness;

    @PostMapping("/createCustomer")
    public ResponseEntity<String> createCustomer(@RequestBody AuthRegisterRequest request) {
        this.userBusiness.createCustomer(request);
        return ResponseEntity.ok("Usuario creado correctamente");
    }

    @GetMapping("/find/{customerId}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable String customerId) {
        return ResponseEntity.ok(this.userBusiness.findCustomerById(customerId));
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String username) {
        this.userBusiness.deleteCustomerByUsername(username);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }

}
