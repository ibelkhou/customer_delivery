package com.carrefour.customer.delivery.enums;

public enum MsgDeliveryResponse {
    NO_CUSTOMER_WITH_USERNAME("No such customer in system."),
    FORBIDDEN_ACTION("The action is forbidden for current customer"),
    TRANSACTION_PROBLEM("Transaction is failed."),
    UKNOWN_PROBLEM("Uknown problem"),
    OK("Well done"),
    ERROR("Error");

    private final String msg;

    MsgDeliveryResponse(final String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
