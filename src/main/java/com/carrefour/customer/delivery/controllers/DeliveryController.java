package com.carrefour.customer.delivery.controllers;

import com.carrefour.customer.delivery.config.JwtConfig;
import com.carrefour.customer.delivery.dto.*;
import com.carrefour.customer.delivery.enums.MsgDeliveryResponse;
import com.carrefour.customer.delivery.exceptions.TechnicalException;
import com.carrefour.customer.delivery.services.DeliveryService;
import com.carrefour.customer.delivery.utils.ResourcePaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(ResourcePaths.BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping(ResourcePaths.Delivery.ENDPOINT_API_RESOURCE_DELIVERY_ALL)
    public DeliveryResponse getAllDelivery(@AuthenticationPrincipal PrincipaleUser principale) {
        log.info("Api to get all delivery for the connected customer");
        if (principale != null) {
            return new DeliveryResponse(
                    HttpStatus.OK,
                    MsgDeliveryResponse.OK,
                    deliveryService.getAllDeliveryByCustomerEmail(principale.getUsername()));
        } else {
            return new DeliveryResponse(HttpStatus.FORBIDDEN, MsgDeliveryResponse.FORBIDDEN_ACTION);
        }
    }

    @PostMapping(ResourcePaths.Delivery.ENDPOINT_API_RESOURCE_DELIVERY_ADD)
    public DeliveryResponse addNewDelivery(
            @AuthenticationPrincipal PrincipaleUser principale,
            @RequestBody @Validated DeliveryRequestDTO request) {

        log.info("Api to add a new delivery to the customer that are connected");

        if (principale != null) {
            try {
                deliveryService.addNewDelivery(principale.getUsername(),
                                            request.getMethod(),
                                            request.getDateTime());
            } catch (TechnicalException ex) {
                return new DeliveryResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        MsgDeliveryResponse.ERROR,
                        ex.getMessage());
            }
            return new DeliveryResponse(
                    HttpStatus.CREATED,
                    MsgDeliveryResponse.OK,
                    true);
        } else {
            return new DeliveryResponse(HttpStatus.FORBIDDEN, MsgDeliveryResponse.FORBIDDEN_ACTION);
        }
    }
}
