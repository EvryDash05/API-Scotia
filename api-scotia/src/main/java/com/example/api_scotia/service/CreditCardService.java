package com.example.api_scotia.service;

import com.example.api_scotia.entities.CreditCardEntity;
import com.example.api_scotia.models.request.CreditCardRequest;
import com.example.api_scotia.models.response.CreditCardResponse;

import java.util.List;

public interface CreditCardService {
    List<CreditCardResponse> getAllCardByCustomerId(String customerId);
    CreditCardResponse createCard(CreditCardRequest request);
    CreditCardResponse getCardById(String idCreditCard);
    CreditCardResponse updateCard(String cardId, CreditCardRequest request);
    void deleteByNumberCard(Long numberCard);
    CreditCardResponse searchCardByNumberCard(Long numberCard);
    CreditCardResponse limitIncrease(String numberCard);
    boolean validateCardInfo(CreditCardEntity card);
}
