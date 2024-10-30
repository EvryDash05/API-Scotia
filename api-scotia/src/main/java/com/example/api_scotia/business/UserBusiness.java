package com.example.api_scotia.business;

import com.example.api_scotia.commons.constants.ErrorConstant;
import com.example.api_scotia.commons.enums.Identifier;
import com.example.api_scotia.commons.utils.Utils;
import com.example.api_scotia.entities.CustomerEntity;
import com.example.api_scotia.entities.RoleEntity;
import com.example.api_scotia.exception.custom.BusinessException;
import com.example.api_scotia.models.request.AuthLoginRequest;
import com.example.api_scotia.models.request.AuthRegisterRequest;
import com.example.api_scotia.models.response.CustomerResponse;
import com.example.api_scotia.repository.CustomerRepository;
import com.example.api_scotia.repository.RoleRepository;
import com.example.api_scotia.service.CustomerFinancialInfoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.management.modelmbean.ModelMBean;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserBusiness {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final CustomerFinancialInfoService customerFinancialInfoService;

    public void createCustomer(AuthRegisterRequest request) {
        Optional<CustomerEntity> findCustomer = customerRepository.findByEmail(request.getEmail());
        if (findCustomer.isPresent()) {
            throw new BusinessException(ErrorConstant.GENERIC_ERROR_CODE, ErrorConstant.INVALID_CREDENTIALS_MESSAGE);
        } else {
            RoleEntity findRole = this.roleRepository.findByName("USER");
            Set<RoleEntity> roles = new HashSet<>();
            roles.add(findRole);
            CustomerEntity customer = CustomerEntity.builder()
                    .customerId(Utils.generateRandomId(Identifier.CUSTOMER.getValue()))
                    .name(request.getName())
                    .lastName(request.getLastName())
                    .numberPhone(request.getNumberPhone())
                    .address(request.getAddress())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .roles(roles)
                    .build();
            this.customerFinancialInfoService.createCustomerFinancialInfo(request.getFinancialInfoRequest(), this.customerRepository.save(customer));
        }
    }

    public CustomerResponse login(AuthLoginRequest request) {
        Optional<CustomerEntity> findCustomer = this.customerRepository.findByEmail(request.getEmail());
        if (findCustomer.isEmpty()) {
            throw new BusinessException(ErrorConstant.GENERIC_ERROR_CODE, ErrorConstant.INVALID_CREDENTIALS_MESSAGE);
        } else {
            if (findCustomer.get().getPassword().equals(request.getPassword())) {
                return this.toResponse(findCustomer.get());
            } else {
                throw new BusinessException(ErrorConstant.GENERIC_ERROR_CODE, ErrorConstant.INVALID_CREDENTIALS_MESSAGE);
            }
        }
    }

    public CustomerResponse findCustomerById(String id) {
        Optional<CustomerEntity> findCustomer = this.customerRepository.findById(id);
        if (findCustomer.isPresent()) {
            return this.toResponse(findCustomer.get());
        }
        throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, ErrorConstant.CUSTOMER_NOT_FOUND);
    }

    public CustomerResponse findCustomerByUsername(String username) {
        Optional<CustomerEntity> findCustomer = this.customerRepository.findByName(username);
        if (findCustomer.isPresent()) {
            return this.toResponse(findCustomer.get());
        }
        throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, ErrorConstant.CUSTOMER_NOT_FOUND);
    }

    public void deleteCustomerByUsername(String username) {
        Optional<CustomerEntity> findCustomer = this.customerRepository.findByName(username);
        if (findCustomer.isEmpty()) {
            throw new BusinessException(ErrorConstant.NOT_FOUND_CODE, ErrorConstant.CUSTOMER_NOT_FOUND);
        }
        this.customerRepository.delete(findCustomer.get());
    }

    private CustomerResponse toResponse(CustomerEntity entity) {
        return new ModelMapper().map(entity, CustomerResponse.class);
    }

}
