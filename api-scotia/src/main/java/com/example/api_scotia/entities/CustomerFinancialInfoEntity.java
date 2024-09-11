package com.example.api_scotia.entities;

import com.example.api_scotia.commons.constants.DataBaseConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DataBaseConstants.CUSTOMER_FINANCIAL_INFO_TABLE)
public class CustomerFinancialInfoEntity {

    @Id
    @Column(name = DataBaseConstants.CUSTOMER_FINANCIAL_INFO_ID, nullable = false, length = 6)
    private String customerFinancialInfoId;

    @Column(name = DataBaseConstants.CUSTOMER_JOB_TYPE, nullable = false, length = 30)
    private String jobType;

    @Column(name = DataBaseConstants.CUSTOMER_MONTHLY_INCOME, nullable = false)
    @Digits(integer = 8, fraction = 2)
    private BigDecimal monthlyIncome;

    @Column(name = DataBaseConstants.CUSTOMER_FIXED_EXPENSES, nullable = false)
    @Digits(integer = 8, fraction = 2)
    private BigDecimal fixedExpenses;

    @Column(name = DataBaseConstants.CUSTOMER_EXCESS_PAYMENT, nullable = false)
    @Digits(integer = 8, fraction = 2)
    private BigDecimal excessPayment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = DataBaseConstants.CLIENT_ID,
            referencedColumnName = DataBaseConstants.CLIENT_ID,
            nullable = false
    )
    private CustomerEntity customer;

}
