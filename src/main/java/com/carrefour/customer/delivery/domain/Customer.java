package com.carrefour.customer.delivery.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_customer_seq")
    @SequenceGenerator(name = "id_customer_seq", sequenceName = "id_customer_seq", allocationSize = 1)
    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String status;
}
