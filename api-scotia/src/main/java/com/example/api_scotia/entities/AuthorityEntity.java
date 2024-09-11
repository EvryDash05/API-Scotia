package com.example.api_scotia.entities;

import com.example.api_scotia.commons.constants.DataBaseConstants;
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
@Table(name = DataBaseConstants.AUTHORITIES_TABLE)
public class AuthorityEntity {

    @Id
    @Column(name = DataBaseConstants.AUTHORITIES_ID, nullable = false, length = 6)
    private String authorityId;

    @Column(name = DataBaseConstants.AUTHORITIES_NAME, nullable = false, length = 50, unique = true)
    private String name;

}
