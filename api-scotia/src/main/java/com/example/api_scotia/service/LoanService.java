package com.example.api_scotia.service;

import com.example.api_scotia.models.request.LoanRequest;
import com.example.api_scotia.models.request.SimulateLoanRequest;
import com.example.api_scotia.models.response.LoanResponse;

import java.util.List;

public interface LoanService {
    List<LoanResponse> getAllLoan();
    LoanResponse createLoan(LoanRequest request);
    void simulateLoan(SimulateLoanRequest request);
    LoanResponse getByLoanId(String id);
    LoanResponse updateByLoanId(String id, LoanRequest request);
    void deleteByLoanId(String id);
    List<LoanResponse> sortLoanByRequestDate(String customerId);
}
