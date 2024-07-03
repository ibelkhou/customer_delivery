package com.carrefour.customer.delivery.utils;

public class ResourcePaths {

    public static final String NAME_APP = "";
    public static final String BASE_URL = NAME_APP + "/api/v1";

    public static final class Customer {

        public static final String ENDPOINT_API_RESOURCE_CUSTOMER_LOGIN = "/customer/login";
        public static final String ENDPOINT_API_RESOURCE_CUSTOMER_TEST_LOGGED = "/customer/testLogged";
    }
    
    public static final class Delivery {

        public static final String ENDPOINT_API_RESOURCE_DELIVERY_ALL = "/delivery/all";
        public static final String ENDPOINT_API_RESOURCE_DELIVERY_ADD = "/delivery/add";
    }
    
}
