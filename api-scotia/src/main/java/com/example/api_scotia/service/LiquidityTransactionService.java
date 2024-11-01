package com.example.api_scotia.service;

import com.example.api_scotia.models.request.LiquidityTransactionRequest;
import com.example.api_scotia.models.response.LiquidityTransactionResponse;

import java.util.List;

public interface LiquidityTransactionService {
    List<LiquidityTransactionResponse> getAllLiquidityTransactionByNumberCard(Long numberCard);
    LiquidityTransactionResponse createLiquidityTransaction(LiquidityTransactionRequest request);
    LiquidityTransactionResponse getByLiquidityTransactionId(String id);
    LiquidityTransactionResponse updateByLiquidityTransactionId(String id, LiquidityTransactionRequest request);
    void deleteByLiquidityTransactionId(String id);
}
