package com.example.api_scotia.entities;

import com.example.api_scotia.commons.constants.DataBaseConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DataBaseConstants.LIQUIDITY_TABLE)
public class LiquidityTransactionEntity {

    @Id
    @Column(name = DataBaseConstants.LIQUIDITY_ID, nullable = false, length = 6)
    private String liquidityId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = DataBaseConstants.LIQUIDITY_TRANSACTION_DATE, nullable = false)
    private LocalDateTime transactionDate;

    @Digits(integer = 8, fraction = 2)
    @Column(name = DataBaseConstants.LIQUIDITY_AMOUNT, nullable = false)
    private BigDecimal transactionAmount;

    @Column(name = DataBaseConstants.LIQUIDITY_STATUS, nullable = false, length = 20)
    private String liquidityStatus;

    @ManyToOne
    @JoinColumn(
            name = DataBaseConstants.LIQUIDITY_CARD_ID,
            referencedColumnName = DataBaseConstants.CREDIT_CARDS_ID
    )
    private CreditCardEntity creditCard;

}
