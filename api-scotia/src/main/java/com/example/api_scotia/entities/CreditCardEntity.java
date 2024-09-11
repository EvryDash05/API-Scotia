package com.example.api_scotia.entities;

import com.example.api_scotia.commons.constants.DataBaseConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DataBaseConstants.CREDIT_CARDS_TABLE)
public class CreditCardEntity {

    @Id
    @Column(name = DataBaseConstants.CREDIT_CARDS_ID, nullable = false, length = 6)
    private String cardId;

    @Column(name = DataBaseConstants.CREDIT_CARDS_NUMBER, nullable = false, unique = true)
    private Long numberCard;

    @Column(name = DataBaseConstants.CREDIT_CARDS_LIMIT, nullable = false)
    @Digits(integer = 8, fraction = 2)
    private BigDecimal limit;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = DataBaseConstants.CREDIT_CARDS_EXPIRATION_DATE, nullable = false)
    private LocalDateTime expirationDate;

    @Column(name = DataBaseConstants.CREDIT_CARDS_TYPE, nullable = false)
    private String typeName;

    @ManyToOne
    @JoinColumn(
            name = DataBaseConstants.CREDIT_CARDS_CLIENT_ID,
            referencedColumnName = DataBaseConstants.CUSTOMER_CLIENT_ID
    )
    private CustomerEntity customer;

    @OneToMany(mappedBy = "creditCard")
    private List<TransactionEntity> transactions;

    @OneToMany(mappedBy = "creditCard")
    private List<LiquidityTransactionEntity> liquidityTransactions;

    @ManyToOne
    @JoinColumn(
            name = DataBaseConstants.CARD_TYPE_ID,
            referencedColumnName = DataBaseConstants.CARD_TYPE_ID
    )
    private CardTypeEntity cardType;

}
