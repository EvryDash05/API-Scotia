package com.example.api_scotia.controllers;

import com.example.api_scotia.models.request.CreditCardRequest;
import com.example.api_scotia.models.response.CreditCardResponse;
import com.example.api_scotia.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CreditCardController {
    private final CreditCardService creditCardService;

    @GetMapping("/getCardsByCustomerId/{customerId}")
    public ResponseEntity<List<CreditCardResponse>> getAllCardsByCustomerId(@PathVariable String customerId) {
        return ResponseEntity.ok(creditCardService.getAllCardByCustomerId(customerId));
    }

    @PostMapping("/createCArd/")
    public ResponseEntity<CreditCardResponse> createCard(@RequestBody CreditCardRequest creditCardRequest) {
        CreditCardResponse creditCardResponse = creditCardService.createCard(creditCardRequest);
        return ResponseEntity.ok(creditCardService.createCard(creditCardRequest));
    }

    @GetMapping("/getCardByID/{idCreditCard}")
    public ResponseEntity<CreditCardResponse> getCardByID(@PathVariable String idCreditCard) {
        return ResponseEntity.ok(creditCardService.getCardById(idCreditCard));

    }

    @DeleteMapping("/deletebynumbercard/{numberCard}")
    public ResponseEntity<String> deleteCardByNumberCard(@PathVariable Long numberCard) {
        creditCardService.deleteByNumberCard(numberCard);
        return ResponseEntity.ok("Tarjeta eliminada");
    }

    @GetMapping("/searchCardByNumberCard/{numberCard}")
    public ResponseEntity<CreditCardResponse> searchCardByNumberCard(@PathVariable Long numberCard) {
        return ResponseEntity.ok(creditCardService.searchCardByNumberCard(numberCard));
    }


}

