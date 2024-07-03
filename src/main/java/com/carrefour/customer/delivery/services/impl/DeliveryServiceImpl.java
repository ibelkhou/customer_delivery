package com.carrefour.customer.delivery.services.impl;

import com.carrefour.customer.delivery.domain.Customer;
import com.carrefour.customer.delivery.domain.Delivery;
import com.carrefour.customer.delivery.dto.DeliveryDTO;
import com.carrefour.customer.delivery.enums.DeliveryStatus;
import com.carrefour.customer.delivery.exceptions.TechnicalException;
import com.carrefour.customer.delivery.mappers.DeliveryMapper;
import com.carrefour.customer.delivery.repositories.DeliveryRepository;
import com.carrefour.customer.delivery.services.CustomerService;
import com.carrefour.customer.delivery.services.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final CustomerService customerService;
    private final DeliveryMapper deliveryMapper;

    @Override
    public List<DeliveryDTO> getAllDeliveryByCustomerEmail(String customerEmail) {
        log.info("Start service to get all delivery for customer {}", customerEmail);
        Customer customer = customerService.findCustomer(customerEmail);

        if (customer != null) {

            log.info("End service to get all delivery for customer {}", customerEmail);
            return deliveryRepository.findAllByCustomer(customer).stream()
                        .map(deliveryMapper::map)
                        .collect(Collectors.toList());
        }

        log.info("End service get all delivery with no customer {}", customerEmail);
        return new ArrayList<>();
    }

    @Override
    public boolean addNewDelivery(String customerEmail, String method, String dateTime) throws TechnicalException {
        log.info("Start service to add a new delivery for customer {}, method {}, dateTime {}", customerEmail, method, dateTime);

        try {
            log.info("Check method {} if its on the enums 'DRIVE, DELIVERY, DELIVERY_TODAY, DELIVERY_ASAP'",  dateTime);
            if (!Arrays.asList("DRIVE", "DELIVERY", "DELIVERY_TODAY", "DELIVERY_ASAP").contains(method)) {
               throw new TechnicalException("Erreur de saisie de la méthode de delivery, il doit être : DRIVE ou DELIVERY ou DELIVERY_TODAY ou DELIVERY_ASAP");
            }

            log.info("Format dateTime {} with pattern dd/MM/yyyy HH:mm",  dateTime);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);

            log.info("Check if the dateTime {} is after now", dateTime);
            if (localDateTime.isBefore(LocalDateTime.now())) {
                throw new TechnicalException("Erreur de saisie de la date, il faut qu'elle doit être supérieure à la date d'aujourd'hui");
            }

            log.info("Create à slot a 1 hour of dateTime {}",  dateTime);
            LocalDateTime localDateTimeStart = localDateTime.minusMinutes(localDateTime.getMinute());
            LocalDateTime localDateTimeEnd = localDateTimeStart.plusHours(1);

            Customer customer = customerService.findCustomer(customerEmail);

            log.info("Search if the is allready have a delivery for the same method {} in the dateTime {} and not DELIVERED or CANCELED", method, dateTime);
            List<Delivery> deliveryExist = deliveryRepository.findAllCreatedDelivery(
                                                customer.getId(), method,
                                                localDateTimeStart, localDateTimeEnd,
                                                Arrays.asList(
                                                        DeliveryStatus.CREATED.getLabel(),
                                                        DeliveryStatus.IN_PROGRESS.getLabel()));

            if (deliveryExist == null || deliveryExist.isEmpty()) {

                log.info("Save a new delivery for customer {}, method {}, dateTime {}", customerEmail, method, dateTime);
                deliveryRepository.save(Delivery.builder()
                                .customer(customer)
                                .deliveryMethod(method)
                                .deliveryDate(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                                .creationDate(new Date())
                                .deliveryStatus(DeliveryStatus.CREATED.getLabel())
                        .build());
                log.info("Saved delivery for customer {}, method {}, dateTime {}", customerEmail, method, dateTime);
            } else {
                throw new TechnicalException("le customer " + customerEmail + " possède déjà une delivery de la même méthode " + method + " dans le même slots " + dateTime);
            }

        } catch(DateTimeException ex) {
            throw new TechnicalException("Erreur de formattage de la date. EX : 02/07/2024 23:15");
        }

        log.info("End service to add a new delivery for customer {}, method {}, dateTime {}", customerEmail, method, dateTime);

        return true;
    }
}
