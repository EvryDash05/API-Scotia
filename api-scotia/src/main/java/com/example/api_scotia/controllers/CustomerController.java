package com.example.api_scotia.controllers;

import com.example.api_scotia.entities.CustomerEntity;
import com.example.api_scotia.repository.CustomerRepository;
import com.example.api_scotia.business.ExcelServiceCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerRepository customerRepository;
    private ExcelServiceCustomer excelService;

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadCustomersExcel() {
        List<CustomerEntity> customers = customerRepository.findAll();
        ByteArrayInputStream in = excelService.customersToExcel(customers);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=customers.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }
}
