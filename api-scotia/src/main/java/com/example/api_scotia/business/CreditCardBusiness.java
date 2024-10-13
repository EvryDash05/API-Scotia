package com.example.api_scotia.business;

import com.example.api_scotia.commons.constants.ErrorConstant;
import com.example.api_scotia.commons.enums.Identifier;
import com.example.api_scotia.commons.utils.Utils;
import com.example.api_scotia.entities.CardTypeEntity;
import com.example.api_scotia.entities.CreditCardEntity;
import com.example.api_scotia.entities.CustomerEntity;
import com.example.api_scotia.exception.custom.BusinessException;
import com.example.api_scotia.models.request.CreditCardRequest;
import com.example.api_scotia.models.response.CreditCardResponse;
import com.example.api_scotia.repository.CardTypeRepository;
import com.example.api_scotia.repository.CreditCardRepository;
import com.example.api_scotia.repository.CustomerRepository;
import com.example.api_scotia.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CreditCardBusiness implements CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final CustomerRepository customerRepository;
    private final CardTypeRepository cardTypeRepository;

    @Override
    public List<CreditCardResponse> getAllCardByCustomerId(String customerId) {
        Optional<CustomerEntity> findCustomer = this.customerRepository.findById(customerId);
        if (findCustomer.isEmpty()) {
            throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, ErrorConstant.CUSTOMER_NOT_FOUND);
        }
        return findCustomer.get().getCreditCard().stream().map(this::toResponse).toList();
    }

    @Override
    public CreditCardResponse createCard(CreditCardRequest request) {
        Optional<CustomerEntity> findCustomer = this.customerRepository.findById(request.getClientId());
        Optional<CardTypeEntity> findcardType = this.cardTypeRepository.findByName(request.getCardType());
        if (findCustomer.isPresent()) {
            CreditCardEntity newCard = CreditCardEntity.builder()
                    .cardId(Utils.generateRandomId(Identifier.CARD.getValue()))
                    .typeName(request.getNameType())
                    .numberCard(request.getCardNumber())
                    .cardType(findcardType.get())
                    .expirationDate(request.getExpirationDate())
                    .limit(request.getCardLimit())
                    .customer(findCustomer.get())
                    .build();

            return this.toResponse(newCard);
        } else {
            throw new BusinessException(ErrorConstant.GENERIC_ERROR_CODE, ErrorConstant.CUSTOMER_NOT_FOUND);
        }

    }


    @Override
    public CreditCardResponse getCardById(String idCreditCard) {
        Optional<CreditCardEntity> findCreditCard = this.creditCardRepository.findById(idCreditCard);
        if (findCreditCard.isEmpty()) {
            throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, ErrorConstant.LOAN_NOT_FOUND_MESSAGE);
        }
        return this.toResponse(findCreditCard.get());

    }

    @Override
    public CreditCardResponse updateCard(String cardId, CreditCardRequest request) {
        return null;
    }

    @Override
    public void deleteByNumberCard(Long numberCard) {
        Optional<CreditCardEntity> findCreditCard = this.creditCardRepository.findByNumberCard(numberCard);
        if (findCreditCard.isEmpty()) {
            throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, ErrorConstant.CARD_NOT_FOUND_MESSAGE);
        }
        this.creditCardRepository.delete(findCreditCard.get());
    }

    @Override
    public CreditCardResponse searchCardByNumberCard(Long numberCard) {
        Optional<CreditCardEntity> findCreditCard = this.creditCardRepository.findByNumberCard(numberCard);
        if (findCreditCard.isEmpty()) {
            throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, ErrorConstant.CARD_NOT_FOUND_MESSAGE);
        }
        return this.toResponse(findCreditCard.get());
    }

    @Override
    public CreditCardResponse limitIncrease(String numberCard) {
        return null;
    }

    @Override
    public boolean validateCardInfo(CreditCardEntity card) {
        return false;
    }

    private CreditCardResponse toResponse(CreditCardEntity entity) {
        return new ModelMapper().map(entity,
                CreditCardResponse.class);
    }
}
