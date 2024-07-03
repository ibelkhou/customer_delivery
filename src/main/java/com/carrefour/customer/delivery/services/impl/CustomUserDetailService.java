package com.carrefour.customer.delivery.services.impl;

import com.carrefour.customer.delivery.domain.Customer;
import com.carrefour.customer.delivery.dto.PrincipaleUser;
import com.carrefour.customer.delivery.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final CustomerService customerService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerService.findCustomer(username);

        return PrincipaleUser.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority("ALL")))
                .build();
    }
}
