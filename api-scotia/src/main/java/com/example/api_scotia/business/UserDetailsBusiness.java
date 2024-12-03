package com.example.api_scotia.business;

import com.example.api_scotia.commons.enums.Identifier;
import com.example.api_scotia.commons.utils.Utils;
import com.example.api_scotia.entities.CustomerEntity;
import com.example.api_scotia.entities.RoleEntity;
import com.example.api_scotia.models.request.AuthCreateUserRequest;
import com.example.api_scotia.models.request.AuthLoginRequest;
import com.example.api_scotia.models.response.AuthResponse;
import com.example.api_scotia.repository.CustomerRepository;
import com.example.api_scotia.repository.RoleRepository;
import com.example.api_scotia.security.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsBusiness implements UserDetailsService {

    private final JwtUtils jwtUtils;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<CustomerEntity> findCustomer = customerRepository.findByEmail(email);

        if (findCustomer.isEmpty()) {
            throw new UsernameNotFoundException("El cliente con el correo " + email + " no existe.");
        }

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        findCustomer.get().getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_" + role)));
        findCustomer.get().getRoles().stream().flatMap(role -> role.getAuthorities().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(findCustomer.get().getEmail(), findCustomer.get().getPassword(), authorityList);
    }

    public AuthResponse createUser(AuthCreateUserRequest createUserRequest) {

        String username = createUserRequest.getUsername();
        String password = createUserRequest.getPassword();

        Set<RoleEntity> roleEntitySet = new HashSet<>();
        roleEntitySet.add(this.roleRepository.findByName("USER"));

        if (roleEntitySet.isEmpty()) {
            throw new IllegalArgumentException("The roles specified does not exist.");
        }

        CustomerEntity userEntity = CustomerEntity.builder()
                .customerId(Utils.generateRandomId(Identifier.CUSTOMER.getValue()))
                .name(username)
                .lastName(createUserRequest.getLastName())
                .email(createUserRequest.getEmail())
                .password(passwordEncoder.encode(password))
                .numberPhone(createUserRequest.getPhone())
                .roles(roleEntitySet)
                .address(createUserRequest.getAddress())
                .build();

        CustomerEntity userSaved = this.customerRepository.save(userEntity);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userSaved.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName()))));
        userSaved.getRoles().stream().flatMap(role -> role.getAuthorities().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = new UsernamePasswordAuthenticationToken(userSaved, password, authorities);

        String accessToken = jwtUtils.createToken(auth);

        return AuthResponse.builder()
                .username(username)
                .lastName(createUserRequest.getLastName())
                .email(createUserRequest.getEmail())
                .message("User created successfully")
                .token(accessToken)
                .build();
    }


    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        String email = authLoginRequest.getEmail();
        String password = authLoginRequest.getPassword();

        Authentication authentication = this.authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication.isAuthenticated()) {
            Optional<CustomerEntity> customerEntity = this.customerRepository.findByEmail(email);
            List<String> roles = customerEntity.get().getRoles()
                    .stream().map(RoleEntity::getName).toList();
            String accessToken = jwtUtils.createToken(authentication);

            return AuthResponse.builder()
                    .customerId(customerEntity.get().getCustomerId())
                    .username(customerEntity.get().getName())
                    .lastName(customerEntity.get().getLastName())
                    .email(email)
                    .phone(customerEntity.get().getNumberPhone())
                    .roles(roles)
                    .token(accessToken)
                    .build();
        }
        return null;
    }

    public Authentication authenticate(String email, String password) {
        UserDetails userDetails = loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
    }

}
