package com.example.api_scotia.business;

import com.example.api_scotia.commons.constants.ErrorConstant;
import com.example.api_scotia.commons.enums.Identifier;
import com.example.api_scotia.commons.utils.Utils;
import com.example.api_scotia.entities.CustomerEntity;
import com.example.api_scotia.entities.CustomerFinancialInfoEntity;
import com.example.api_scotia.exception.custom.BusinessException;
import com.example.api_scotia.models.request.CustomerFinancialInfoRequest;
import com.example.api_scotia.repository.CustomerFinancialInfoRepository;
import com.example.api_scotia.repository.CustomerRepository;
import com.example.api_scotia.service.CustomerFinancialInfoService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomerFinancialInfoBusiness implements CustomerFinancialInfoService {

    private final CustomerFinancialInfoRepository customerFinancialInfoRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createCustomerFinancialInfo(CustomerFinancialInfoRequest request) {
        Optional<CustomerEntity> findCustomer = this.customerRepository.findById(request.getClientId());
        if(findCustomer.isPresent()) {
            CustomerFinancialInfoEntity newInfo= CustomerFinancialInfoEntity.builder()
                    .customerFinancialInfoId(Utils.generateRandomId(Identifier.FINANCIAL_INFO.getValue()))
                    .jobType(request.getJobType())
                    .monthlyIncome(request.getMonthlyIncome())
                    .fixedExpenses(request.getFixedExpenses())
                    .excessPayment(request.getExcessPayment())
                    .customer(findCustomer.get())
                    .build();
            this.customerFinancialInfoRepository.save(newInfo);
        } else {
            throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, ErrorConstant.CUSTOMER_NOT_FOUND);
        }

    }

    @Override
    public void updateFinancialInfoById(String id, CustomerFinancialInfoRequest request) {

    }

    @Override
    public void deleteCustomerFinancialInfoRequestByCustomerId(String customerId) {
        Optional<CustomerEntity> findCustomer = this.customerRepository.findById(customerId);
        if(findCustomer.isEmpty()){
            throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, ErrorConstant.CUSTOMER_NOT_FOUND);
        }
     this.customerFinancialInfoRepository.delete(findCustomer.get().getCustomerFinancialInfo());
    }
    
}
