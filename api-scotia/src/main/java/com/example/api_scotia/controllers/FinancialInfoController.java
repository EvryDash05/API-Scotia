package com.example.api_scotia.controllers;

import com.example.api_scotia.service.CustomerFinancialInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FinancialInfoController {

    private final CustomerFinancialInfoService customerFinancialInfoService;

    @DeleteMapping("/deleteFinancialInfo/{id}")
    public ResponseEntity<String> deleteFinancialInfo(@PathVariable String id) {
        customerFinancialInfoService.deleteCustomerFinancialInfoRequestByCustomerId(id);
        return ResponseEntity.ok("Información financiera del cliente eliminada");
    }
}
