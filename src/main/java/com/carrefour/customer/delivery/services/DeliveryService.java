package com.carrefour.customer.delivery.services;

import com.carrefour.customer.delivery.domain.Customer;
import com.carrefour.customer.delivery.domain.Delivery;
import com.carrefour.customer.delivery.dto.DeliveryDTO;
import com.carrefour.customer.delivery.exceptions.TechnicalException;

import java.util.List;

public interface DeliveryService {

    List<DeliveryDTO> getAllDeliveryByCustomerEmail(String customerEmail);

    boolean addNewDelivery(String customerEmail, String method, String dateTime) throws TechnicalException;
}
