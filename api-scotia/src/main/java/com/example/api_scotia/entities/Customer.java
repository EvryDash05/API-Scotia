package com.example.api_scotia.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_customers")
public class Customer {

    @Id
    @Column(name = "customer_id", nullable = false, length = 7)
    private String customerId;

    @Column(name = "customer_name", nullable = false, length = 100)
    private String name;

    @Column(name = "customer_phone", nullable = false, length = 11)
    private String numberPhone;

    @Column(name = "customer_email", nullable = false, length = 100)
    private String email;

}
