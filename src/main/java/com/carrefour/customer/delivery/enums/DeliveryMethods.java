package com.carrefour.customer.delivery.enums;

public enum DeliveryMethods {
    DRIVE("DRIVE"),
    DELIVERY("DELIVERY"),
    DELIVERY_TODAY("DELIVERY TODAY"),
    DELIVERY_ASAP("DELIVERY ASAP");

    public final String label;
    private DeliveryMethods(String label) {
        this.label = label;
    }
}
