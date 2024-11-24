package com.example.api_scotia.business;

import com.example.api_scotia.commons.constants.ErrorConstant;
import com.example.api_scotia.commons.enums.Identifier;
import com.example.api_scotia.commons.utils.Utils;
import com.example.api_scotia.entities.CustomerEntity;
import com.example.api_scotia.entities.LoanEntity;
import com.example.api_scotia.exception.custom.BusinessException;
import com.example.api_scotia.models.request.LoanRequest;
import com.example.api_scotia.models.request.SimulateLoanRequest;
import com.example.api_scotia.models.response.LoanResponse;
import com.example.api_scotia.repository.CustomerRepository;
import com.example.api_scotia.repository.LoanRepository;
import com.example.api_scotia.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanBusiness implements LoanService {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final CustomerPayBusiness customerPayBusiness;
    private final EmailBusiness emailBusiness;

    @Override
    public List<LoanResponse> getAllLoan() {
        return this.loanRepository.findAll().stream().map(this::toResponse)
                .toList();
    }

    @Override
    public LoanResponse createLoan(LoanRequest request) {
        Optional<CustomerEntity> findCustomer = this.customerRepository.findById(request.getCustomerId());
        if (findCustomer.isPresent()) {
            LoanEntity newLoan = LoanEntity.builder()
                    .loanId(Utils.generateRandomId(Identifier.LOAN.getValue()))
                    .requestType("Pendiente")
                    .totalAmount(request.getAmount())
                    .status("Pendiente")
                    .requestDate(LocalDateTime.now())
                    .installments(request.getInstallments())
                    .customer(findCustomer.get())
                    .build();
            this.customerPayBusiness.createCustomerPay(this.loanRepository.save(newLoan));
            log.info("Se crea el préstamo {}: "+newLoan.toString());
            this.emailBusiness.sendEmail("loanConfirmationMessage", findCustomer.get().getEmail(),
                    parametersLoanConfirmationMessage(findCustomer.get(), newLoan));
            return this.toResponse(newLoan);
        } else {
            throw new BusinessException(ErrorConstant.GENERIC_ERROR_CODE, ErrorConstant.CUSTOMER_NOT_FOUND);
        }
    }

    @Override
    public void simulateLoan(SimulateLoanRequest request) {
        BigDecimal monthlyPaymentAmount = request.getAmount().divide(new BigDecimal(request.getInstallments()), RoundingMode.HALF_UP);
        log.info("Simulando préstamo");
        this.emailBusiness.sendEmail("simulateLoanMessage", request.getEmail(),
                this.parametersSimulateLoanMessage(request, monthlyPaymentAmount));
    }

    @Override
    public LoanResponse getByLoanId(String id) {
        Optional<LoanEntity> findLoan = this.loanRepository.findById(id);
        if (findLoan.isEmpty()) {
            throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, ErrorConstant.LOAN_NOT_FOUND_MESSAGE);
        }
        return this.toResponse(findLoan.get());
    }

    @Override
    public LoanResponse updateByLoanId(String id, LoanRequest request) {
        return null;
    }

    @Override
    public void deleteByLoanId(String id) {
        Optional<LoanEntity> findLoan = this.loanRepository.findById(id);
        if (findLoan.isEmpty()) {
            throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, ErrorConstant.LOAN_NOT_FOUND_MESSAGE);
        }
        this.loanRepository.delete(findLoan.get());
    }

    @Override
    public List<LoanResponse> sortLoanByRequestDate(String customerId) {
        return List.of();
    }

    private LoanResponse toResponse(LoanEntity entity){
        return new ModelMapper().map(entity, LoanResponse.class);
    }

    private Map<String, Object> parametersLoanConfirmationMessage(CustomerEntity customer, LoanEntity loan) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", customer.getName());
        parameters.put("lastName", customer.getLastName());
        parameters.put("totalAmount", loan.getTotalAmount());
        parameters.put("installments", loan.getInstallments());
        return parameters;
    }

    private Map<String, Object> parametersSimulateLoanMessage(SimulateLoanRequest request, BigDecimal quote){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", request.getName());
        parameters.put("dni", request.getDni());
        parameters.put("amount", request.getAmount());
        parameters.put("amountQuote", quote);
        parameters.put("installments", request.getInstallments());
        parameters.put("email", request.getEmail());
        return parameters;
    }

}
