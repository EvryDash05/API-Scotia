package com.example.api_scotia.entities;

import com.example.api_scotia.commons.constants.DataBaseConstants;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DataBaseConstants.CLIENT_TABLE)
public class CustomerEntity {

    @Id
    @Column(name = DataBaseConstants.CUSTOMER_CLIENT_ID, nullable = false, length = 6)
    private String customerId;

    @Column(name = DataBaseConstants.CLIENT_NAME, nullable = false, length = 100)
    private String name;

    @Column(name = DataBaseConstants.CLIENT_LAST_NAME, nullable = false, length = 100)
    private String lastName;

    @Column(name = DataBaseConstants.CLIENT_PHONE, nullable = false, length = 11)
    private String numberPhone;

    @Column(name = DataBaseConstants.CLIENT_ADDRESS, nullable = false, length = 100)
    private String address;

    @Column(name = DataBaseConstants.CLIENT_EMAIL, nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = DataBaseConstants.CLIENT_PASSWORD, nullable = false, length = 100)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = DataBaseConstants.CUSTOMER_ROLE_TABLE,
            joinColumns = @JoinColumn(name = DataBaseConstants.CUSTOMER_ROLE_CLIENT_ID),
            inverseJoinColumns = @JoinColumn(name = DataBaseConstants.CUSTOMER_ROLE_ROLE_ID)
    )
    private Set<RoleEntity> roles;

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
    private CustomerFinancialInfoEntity customerFinancialInfo;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<LoanEntity> loanList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<CreditCardEntity> creditCard;

}
