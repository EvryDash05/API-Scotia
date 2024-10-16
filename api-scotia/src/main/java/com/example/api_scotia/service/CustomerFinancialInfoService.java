package com.example.api_scotia.service;

import com.example.api_scotia.entities.CustomerEntity;
import com.example.api_scotia.models.request.CustomerFinancialInfoRequest;

public interface CustomerFinancialInfoService {
    void createCustomerFinancialInfo(CustomerFinancialInfoRequest request, CustomerEntity customerEntity);
    void updateFinancialInfoById(String id, CustomerFinancialInfoRequest request);
    void deleteCustomerFinancialInfoRequestByCustomerId(String customerId);
}
