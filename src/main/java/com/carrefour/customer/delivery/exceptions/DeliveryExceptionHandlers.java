package com.carrefour.customer.delivery.exceptions;

import com.carrefour.customer.delivery.dto.DeliveryResponse;
import com.carrefour.customer.delivery.enums.MsgDeliveryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class DeliveryExceptionHandlers {

    @ExceptionHandler(TechnicalException.class)
    public DeliveryResponse handleTechnicalException(TechnicalException ex) {
        return new DeliveryResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                MsgDeliveryResponse.ERROR,
                ex.getExceptionDeliveryType()+" "+ex.getMessage());
    }
}
