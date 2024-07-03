package com.carrefour.customer.delivery.mappers;

import com.carrefour.customer.delivery.domain.Delivery;
import com.carrefour.customer.delivery.dto.DeliveryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    DeliveryDTO map(Delivery delivery);
}
