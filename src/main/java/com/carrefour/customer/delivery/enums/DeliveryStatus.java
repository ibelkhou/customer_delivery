package com.carrefour.customer.delivery.enums;

public enum DeliveryStatus {
    CREATED("CREATED"),
    IN_PROGRESS("IN PROGRESS"),
    DELIVERED("DELIVERED"),
    CANCELED("CANCELED");

    public final String label;

    public String getLabel() {
        return this.label;
    }

    private DeliveryStatus(String label) {
        this.label = label;
    }
}
