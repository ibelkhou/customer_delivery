package com.carrefour.customer.delivery.services.impl;

import com.carrefour.customer.delivery.domain.Customer;
import com.carrefour.customer.delivery.repositories.CustomerRepository;
import com.carrefour.customer.delivery.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer findCustomer(String email) {
        log.info("Service to find a customer by his mail {}", email);
        return customerRepository.findByEmail(email);
    }
}
