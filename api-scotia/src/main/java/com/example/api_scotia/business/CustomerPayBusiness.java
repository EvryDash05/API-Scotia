package com.example.api_scotia.business;

import com.example.api_scotia.commons.constants.ErrorConstant;
import com.example.api_scotia.commons.enums.Identifier;
import com.example.api_scotia.commons.utils.Utils;
import com.example.api_scotia.entities.ClientPaymentEntity;
import com.example.api_scotia.entities.LoanEntity;
import com.example.api_scotia.exception.custom.BusinessException;
import com.example.api_scotia.models.request.CustomerPayRequest;
import com.example.api_scotia.models.response.CustomerPayResponse;
import com.example.api_scotia.repository.CustomerPaymentRepository;
import com.example.api_scotia.repository.LoanRepository;
import com.example.api_scotia.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerPayBusiness implements PaymentService {

    private final CustomerPaymentRepository customerPaymentRepository;
    private final LoanRepository loanRepository;

    @Override
    public void createCustomerPay(LoanEntity loan) {
        BigDecimal monthlyPaymentAmount = loan.getTotalAmount().divide(new BigDecimal(loan.getInstallments()), BigDecimal.ROUND_HALF_UP);

        LocalDate paymentDate = loan.getRequestDate().toLocalDate();

        for (int i = 1; i <= loan.getInstallments(); i++) {
            ClientPaymentEntity payment = ClientPaymentEntity.builder()
                    .paymentId(Utils.generateRandomId(Identifier.CUSTOMER_PAY.getValue()))
                    .paymentDate(paymentDate)
                    .paymentAmount(monthlyPaymentAmount)
                    .paymentStatus(0)
                    .loan(loan)
                    .build();

            this.customerPaymentRepository.save(payment);
            paymentDate = paymentDate.plusMonths(1);
        }
    }

    @Override
    public List<CustomerPayResponse> getAllCustomerPay() {
        return List.of();
    }

    @Override
    public CustomerPayResponse getCustomerPayById(String idPay) {
        Optional<ClientPaymentEntity> findClientPayment = this.customerPaymentRepository.findById(idPay);
        if(findClientPayment.isEmpty()) {
            throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, "Payment not found");
        }
        return this.toResponse(findClientPayment.get());
    }

    @Override
    public CustomerPayResponse updateCustomerPay(String idPay, CustomerPayRequest request) {
        return null;
    }

    @Override
    public void deleteCustomerPay(String idPay) {

    }

    @Override
    public CustomerPayResponse searchCustomerPayByDate(LocalDate datePay) {
        return null;
    }

    @Override
    public CustomerPayResponse searchCustomerPayByState(int statePay) {
        return null;
    }

    @Override
    public List<CustomerPayResponse> searchCustomerPayByLoanId(String loanId) {
        Optional<LoanEntity> findLoan = this.loanRepository.findById(loanId);
        if(findLoan.isEmpty()) {
            throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, ErrorConstant.LOAN_NOT_FOUND_MESSAGE);
        }
        return findLoan.get().getPayments().stream()
                .sorted(Comparator.comparing(ClientPaymentEntity::getPaymentDate)) // Ordena por fecha ascendente
                .map(this::toResponse).toList();
    }

    private CustomerPayResponse toResponse(ClientPaymentEntity entity){
        return new ModelMapper().map(entity, CustomerPayResponse.class);
    }

}
