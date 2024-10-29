package com.example.api_scotia.service;

import com.example.api_scotia.entities.LoanEntity;
import com.example.api_scotia.models.request.CustomerPayRequest;
import com.example.api_scotia.models.response.CustomerPayResponse;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    void createCustomerPay(LoanEntity loan);
    List<CustomerPayResponse> getAllCustomerPay();
    CustomerPayResponse getCustomerPayById(String idPay);
    CustomerPayResponse updateCustomerPay(String idPay, CustomerPayRequest request);
    void deleteCustomerPay(String idPay);
    //boolean validateCustomerPayInfo(ClientPaymentEntity customerpay);
    CustomerPayResponse searchCustomerPayByDate(LocalDate datePay);
    CustomerPayResponse searchCustomerPayByState(int statePay);
    List<CustomerPayResponse> searchCustomerPayByLoanId(String loanId);
}
