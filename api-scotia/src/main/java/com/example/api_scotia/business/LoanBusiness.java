package com.example.api_scotia.business;

import com.example.api_scotia.commons.constants.ErrorConstant;
import com.example.api_scotia.commons.enums.Identifier;
import com.example.api_scotia.commons.utils.Utils;
import com.example.api_scotia.entities.CustomerEntity;
import com.example.api_scotia.entities.LoanEntity;
import com.example.api_scotia.exception.custom.BusinessException;
import com.example.api_scotia.models.request.LoanRequest;
import com.example.api_scotia.models.response.LoanResponse;
import com.example.api_scotia.repository.CustomerRepository;
import com.example.api_scotia.repository.LoanRepository;
import com.example.api_scotia.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class LoanBusiness implements LoanService {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

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
            this.loanRepository.save(newLoan);
            return this.toResponse(newLoan);
        } else {
            throw new BusinessException(ErrorConstant.GENERIC_ERROR_CODE, ErrorConstant.CUSTOMER_NOT_FOUND);
        }
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

}
