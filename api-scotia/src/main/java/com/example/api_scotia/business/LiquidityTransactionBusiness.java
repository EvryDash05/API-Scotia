package com.example.api_scotia.business;

import com.example.api_scotia.commons.constants.ErrorConstant;
import com.example.api_scotia.commons.enums.Identifier;
import com.example.api_scotia.commons.utils.Utils;
import com.example.api_scotia.entities.CreditCardEntity;
import com.example.api_scotia.entities.LiquidityTransactionEntity;
import com.example.api_scotia.exception.custom.BusinessException;
import com.example.api_scotia.models.request.LiquidityTransactionRequest;
import com.example.api_scotia.models.response.LiquidityTransactionResponse;
import com.example.api_scotia.repository.CreditCardRepository;
import com.example.api_scotia.repository.CustomerRepository;
import com.example.api_scotia.repository.LiquidityTransactionRepository;
import com.example.api_scotia.service.LiquidityTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class LiquidityTransactionBusiness implements LiquidityTransactionService {

    private final LiquidityTransactionRepository liquidityTransactionRepository;
    private final CustomerRepository customerRepository;
    private final CreditCardRepository creditCardRepository;

    @Override
    public List<LiquidityTransactionResponse> getAllLiquidityTransactionByNumberCard(Long numberCard) {
        Optional<CreditCardEntity> findCreditCard = this.creditCardRepository.findByNumberCard(numberCard);
        if (findCreditCard.isEmpty()) {
            throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, ErrorConstant.CARD_NOT_FOUND_MESSAGE);
        }
        return findCreditCard.get().getLiquidityTransactions().stream().map(this::toResponse).toList();
    }

    @Override
    public LiquidityTransactionResponse createLiquidityTransaction(LiquidityTransactionRequest request) {
        Optional<CreditCardEntity> findCreditCard = this.creditCardRepository.findByNumberCard(request.getNumberCard());
        if (findCreditCard.isPresent()) {
            LiquidityTransactionEntity newLiquidityTransaction = LiquidityTransactionEntity.builder()
                    .liquidityId(Utils.generateRandomId(Identifier.LIQUIDITY_TRANSACTION.getValue()))
                    .transactionDate(LocalDateTime.now())
                    .transactionAmount(request.getAmount())
                    .liquidityStatus("Pendiente")
                    .creditCard(findCreditCard.get())
                    .build();
            return this.toResponse(liquidityTransactionRepository.save(newLiquidityTransaction));
        }else {
            throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, ErrorConstant.CARD_NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public LiquidityTransactionResponse getByLiquidityTransactionId(String id) {
        Optional<LiquidityTransactionEntity> findLiquidityTransaction = this.liquidityTransactionRepository.findById(id);
        if (findLiquidityTransaction.isEmpty()) {
            throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, "LIQUIDITY_TRANSACTION_NOT_FOUND");
        }
        return this.toResponse(findLiquidityTransaction.get());
    }

    @Override
    public LiquidityTransactionResponse updateByLiquidityTransactionId(String id, LiquidityTransactionRequest request) {
        return null;
    }

    @Override
    public void deleteByLiquidityTransactionId(String id) {
      Optional<LiquidityTransactionEntity> findLiquidityTransaction = this.liquidityTransactionRepository.findById(id);
      if (findLiquidityTransaction.isEmpty()) {
          throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, "LIQUIDITY_TRANSACTION_NOT_FOUND");
      }
      this.liquidityTransactionRepository.delete(findLiquidityTransaction.get());
    }

    private LiquidityTransactionResponse toResponse(LiquidityTransactionEntity entity) {return new ModelMapper().map(entity, LiquidityTransactionResponse.class);}
}

