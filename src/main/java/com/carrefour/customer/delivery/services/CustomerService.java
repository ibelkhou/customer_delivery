package com.carrefour.customer.delivery.services;

import com.carrefour.customer.delivery.domain.Customer;

public interface CustomerService {

    Customer findCustomer(String email);
}
