package com.carrefour.customer.delivery.dto;

import com.carrefour.customer.delivery.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String status;
}
