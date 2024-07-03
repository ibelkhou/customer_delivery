package com.carrefour.customer.delivery.dto;

import com.carrefour.customer.delivery.domain.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryDTO {

    private Long id;
    private Customer customer;
    private String deliveryMethod;
    private Date deliveryDate;
    private Date creationDate;
    private String deliveryStatus;
}
