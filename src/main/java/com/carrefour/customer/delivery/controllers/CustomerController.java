package com.carrefour.customer.delivery.controllers;

import com.carrefour.customer.delivery.config.JwtConfig;
import com.carrefour.customer.delivery.dto.DeliveryResponse;
import com.carrefour.customer.delivery.dto.LoginRequestDTO;
import com.carrefour.customer.delivery.dto.LoginResponseDTO;
import com.carrefour.customer.delivery.dto.PrincipaleUser;
import com.carrefour.customer.delivery.enums.MsgDeliveryResponse;
import com.carrefour.customer.delivery.utils.ResourcePaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
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
public class CustomerController {

    private final JwtConfig jwtConfig;
    private final AuthenticationManager authenticationManager;


    @PostMapping(ResourcePaths.Customer.ENDPOINT_API_RESOURCE_CUSTOMER_LOGIN)
    public DeliveryResponse loginUser(@RequestBody @Validated LoginRequestDTO request) {

        log.info("Start login customer to have access to the API");
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        PrincipaleUser principale = (PrincipaleUser) authentication.getPrincipal();

        if (principale != null) {

            log.info("Custoemr is logged in and genered the accessToken");
            return new DeliveryResponse(
                    HttpStatus.OK,
                    MsgDeliveryResponse.OK,
                    LoginResponseDTO.builder()
                            .accessToken(jwtConfig.token(principale.getId(),
                                    principale.getEmail(), principale.getAuthorities().stream()
                                            .map(GrantedAuthority::getAuthority).toList()))
                            .build());
        } else {

            log.info("Custoemr is NOT logged in");
            return new DeliveryResponse(HttpStatus.BAD_REQUEST, MsgDeliveryResponse.NO_CUSTOMER_WITH_USERNAME);
        }
    }

    @GetMapping(ResourcePaths.Customer.ENDPOINT_API_RESOURCE_CUSTOMER_TEST_LOGGED)
    public DeliveryResponse testLogin(@AuthenticationPrincipal PrincipaleUser principale) {

        log.info("Api to test if the customer is logged IN");
        if (principale != null) {
            return new DeliveryResponse(
                    HttpStatus.OK,
                    MsgDeliveryResponse.OK,
                    "You are logged in "+ principale.getUsername()+" !");
        } else {
            return new DeliveryResponse(HttpStatus.FORBIDDEN, MsgDeliveryResponse.FORBIDDEN_ACTION);
        }
    }

}
