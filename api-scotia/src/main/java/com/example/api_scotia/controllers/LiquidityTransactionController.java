package com.example.api_scotia.controllers;


import com.example.api_scotia.models.request.LiquidityTransactionRequest;
import com.example.api_scotia.service.LiquidityTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LiquidityTransactionController {
    private final LiquidityTransactionService liquidityTransactionService;

    @DeleteMapping("/deleteLiquidityTransaction/{id}")
    public ResponseEntity<String> deleteLiquidityTransactionInfo(@PathVariable String id) {
        liquidityTransactionService.deleteByLiquidityTransactionId(id);
        return ResponseEntity.ok("Transacsion de liquidez eliminada");
    }

    @PostMapping("/createLiquidityTransaction")
    public ResponseEntity<String> createLiquidityTransaction(@RequestBody LiquidityTransactionRequest request) {
        liquidityTransactionService.createLiquidityTransaction(request);
        return ResponseEntity.ok("Transacsion de liquidez creada");
    }

    @PutMapping("/updateLiquidityTransaction/{id}")
    public ResponseEntity<String> updateByLiquidityTransactionId(@PathVariable String id, @RequestBody LiquidityTransactionRequest request) {
        liquidityTransactionService.updateByLiquidityTransactionId(id,request);
        return ResponseEntity.ok("Transacsion de liquidez actualizada");
    }
    @GetMapping("/getLiquidityTransaction/{id}")
    public ResponseEntity<String> getLiquidityTransaction(@PathVariable String id) {
        liquidityTransactionService.getByLiquidityTransactionId(id);
        return ResponseEntity.ok("Transacsion de liquidez obtenida");
    }
}
