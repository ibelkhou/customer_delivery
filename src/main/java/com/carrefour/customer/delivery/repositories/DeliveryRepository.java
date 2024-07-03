package com.carrefour.customer.delivery.repositories;

import com.carrefour.customer.delivery.domain.Customer;
import com.carrefour.customer.delivery.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findAllByCustomer(Customer customer);

    @Query("SELECT delivery FROM Delivery delivery " +
            "WHERE delivery.customer.id = :customerId " +
            "AND delivery.deliveryMethod = :method " +
            "AND delivery.deliveryDate BETWEEN :startDate AND :endDate " +
            "AND delivery.deliveryStatus in :status")
    List<Delivery> findAllCreatedDelivery(
            Long customerId,
            String method,
            LocalDateTime startDate, LocalDateTime endDate,
            List<String> status);
}
