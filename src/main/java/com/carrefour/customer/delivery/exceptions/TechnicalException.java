package com.carrefour.customer.delivery.exceptions;

import com.carrefour.customer.delivery.enums.ExceptionDeliveryType;

public class TechnicalException extends Exception {

    private final ExceptionDeliveryType exceptionDeliveryType;

    public TechnicalException(String message) {
        super(message);
        this.exceptionDeliveryType = ExceptionDeliveryType.TECHNICAL_EXCEPTION;
    }

    public TechnicalException(ExceptionDeliveryType exceptionDeliveryType) {
        super();
        this.exceptionDeliveryType = exceptionDeliveryType;
    }

    public TechnicalException(String message, ExceptionDeliveryType exceptionDeliveryType) {
        super(message);
        this.exceptionDeliveryType = exceptionDeliveryType;
    }

    public String getExceptionDeliveryType() {
        return this.exceptionDeliveryType.name();
    }
}
