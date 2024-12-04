package com.example.api_scotia.controllers;

import com.example.api_scotia.models.request.LoanRequest;
import com.example.api_scotia.models.request.SimulateLoanRequest;
import com.example.api_scotia.models.response.LoanResponse;
import com.example.api_scotia.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/createLoan")
    public ResponseEntity<?> createLoan(@RequestBody LoanRequest request){
        return ResponseEntity.ok(loanService.createLoan(request));
    }

    @PostMapping("/simulateLoan")
    public ResponseEntity<String> simulateLoan(@RequestBody SimulateLoanRequest request){
        loanService.simulateLoan(request);
        return ResponseEntity.ok("Simulación exitosa");
    }

    @GetMapping("/getLoansByCustomerId/{customerId}")
    public ResponseEntity<List<LoanResponse>> getLoansByCustomerId(@PathVariable String customerId){
        return ResponseEntity.ok(loanService.getLoansByCustomerId(customerId));
    }


}
