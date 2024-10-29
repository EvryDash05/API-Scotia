package com.example.api_scotia.controllers;

import com.example.api_scotia.business.CustomerPayBusiness;
import com.example.api_scotia.models.response.CustomerPayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CustomerPayController {

    private final CustomerPayBusiness customerPayBusiness;

    @GetMapping("/getInstallments/ByLoanId/{loanId}")
    public ResponseEntity<List<CustomerPayResponse>> getInstallmentsByLoanId(@PathVariable String loanId){
        return ResponseEntity.ok(this.customerPayBusiness.searchCustomerPayByLoanId(loanId));
    }

}
