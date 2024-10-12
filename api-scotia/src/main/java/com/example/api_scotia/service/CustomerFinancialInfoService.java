package com.example.api_scotia.service;

import com.example.api_scotia.models.request.CustomerFinancialInfoRequest;

public interface CustomerFinancialInfoService {
    void createCustomerFinancialInfo(CustomerFinancialInfoRequest request);
    void updateFinancialInfoById(String id, CustomerFinancialInfoRequest request);
    void deleteCustomerFinancialInfoRequestByCustomerId(String customerId);
}
