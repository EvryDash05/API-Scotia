package com.example.api_scotia.entities;

import com.example.api_scotia.commons.constants.DataBaseConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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
@Table(name = DataBaseConstants.TRANSACTION_TABLE)
public class TransactionEntity {

    @Id
    @Column(name = DataBaseConstants.TRANSACTION_ID, nullable = false, length = 6)
    private String transactionId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = DataBaseConstants.TRANSACTION_DATE, nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = DataBaseConstants.TRANSACTION_AMOUNT, nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(
            name = DataBaseConstants.TRANSACTION_CARD_ID,
            referencedColumnName = DataBaseConstants.CREDIT_CARDS_ID
    )
    private CreditCardEntity creditCard;

}
