package com.carrefour.customer.delivery.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="delivery")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_delivery_seq")
    @SequenceGenerator(name = "id_delivery_seq", sequenceName = "id_delivery_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String deliveryMethod;
    private Date deliveryDate;
    private Date creationDate;
    private String deliveryStatus;
}
