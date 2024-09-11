package com.example.api_scotia.entities;

import com.example.api_scotia.commons.constants.DataBaseConstants;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DataBaseConstants.CARD_TYPE_TABLE)
public class CardTypeEntity {

    @Id
    @Column(name = DataBaseConstants.CARD_TYPE_ID, nullable = false, length = 6)
    private String typeId;

    @Column(name = DataBaseConstants.CARD_TYPE_NAME, nullable = false, length = 6)
    private String name;

    @OneToMany(mappedBy = "cardType", cascade = CascadeType.ALL)
    private List<CreditCardEntity> creditCards;

}
