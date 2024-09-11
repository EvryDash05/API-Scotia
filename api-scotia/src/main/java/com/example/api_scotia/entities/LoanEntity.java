package com.example.api_scotia.entities;

import com.example.api_scotia.commons.constants.DataBaseConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DataBaseConstants.LOAN_TABLE)
public class LoanEntity {

    @Id
    @Column(name = DataBaseConstants.LOAN_ID, nullable = false, length = 6)
    private String loanId;

    @Column(name = DataBaseConstants.LOAN_REQUEST_TYPE, nullable = false, length = 20)
    private String requestType;

    @Column(name = DataBaseConstants.LOAN_TOTAL_AMOUNT, nullable = false)
    @Digits(integer = 8, fraction = 2)
    private BigDecimal totalAmount;

    @Column(name = DataBaseConstants.LOAN_STATUS, nullable = false, length = 50)
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = DataBaseConstants.LOAN_DATE, nullable = false)
    private LocalDateTime requestDate;

    @Column(name = DataBaseConstants.LOAN_INSTALLMENTS, nullable = false)
    private Integer installments;

    @ManyToOne
    @JoinColumn(
            name = DataBaseConstants.CUSTOMER_CLIENT_ID,
            referencedColumnName = DataBaseConstants.CUSTOMER_CLIENT_ID,
            nullable = false)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "loan")
    private Set<ClientPaymentEntity> payments = new HashSet<>();

}
