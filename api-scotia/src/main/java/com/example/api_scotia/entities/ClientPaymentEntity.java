package com.example.api_scotia.entities;

import com.example.api_scotia.commons.constants.DataBaseConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DataBaseConstants.CLIENT_PAYMENT_TABLE)
public class ClientPaymentEntity {

    @Id
    @Column(name = DataBaseConstants.CLIENT_PAYMENT_ID, nullable = false, length = 6)
    private String paymentId;

    @Column(name = DataBaseConstants.CLIENT_PAYMENT_DATE, nullable = false)
    private LocalDate paymentDate;

    @Column(name = DataBaseConstants.CLIENT_PAYMENT_STATUS, nullable = false)
    private Integer paymentStatus;

    @Column(name = DataBaseConstants.CLIENT_PAYMENT_AMOUNT, nullable = false)
    @Digits(integer = 8, fraction = 2)
    private BigDecimal paymentAmount;

    @ManyToOne
    @JoinColumn(
            name = DataBaseConstants.LOAN_ID,
            referencedColumnName = DataBaseConstants.LOAN_ID
    )
    private LoanEntity loan;

}
