package com.example.api_scotia.entities;

import com.example.api_scotia.commons.constants.DataBaseConstants;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DataBaseConstants.ROLES_TABLE)
public class RoleEntity {

    @Id
    @Column(name = DataBaseConstants.ROLES_ID, nullable = false, length = 6)
    private String roleId;

    @Column(name = DataBaseConstants.ROLES_NAME, nullable = false, length = 50)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = DataBaseConstants.ROLE_AUTHORITY_TABLE,
        joinColumns = @JoinColumn(name = DataBaseConstants.ROLE_AUTHORITY_ROLE_ID),
        inverseJoinColumns = @JoinColumn(name = DataBaseConstants.ROLE_AUTHORITY_AUTHORITY_ID)
    )
    private Set<AuthorityEntity> authorities = new HashSet<>();

}
