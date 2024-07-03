# customer_delivery

Customer Delivery is project that help customer to create a delivery with a specific method in a desired time slots.

 *** The available delivery methods are: `DRIVE`, `DELIVERY`, `DELIVERY_TODAY`, `DELIVERY_ASAP`.

 *** The time slots are specific to the delivery method

To test the project, you must have some PRE-REQUEST : 
- JDK 21
- MAVEN
- Postman


1. Clone the projet from Github : https://github.com/ibelkhou/customer_delivery.git
2. Build the project with MAVEN
3. Start the project

After starting the project, you can open Postman, and start to test the defined API :
* POST : /api/v1/customer/login
  * Request : {
                "username": "ibel@test.com",
                "password": "test"
                }
* GET : /api/v1/delivery/all
  * add the bearer in the request with the value of the accessToken
* POST : /api/v1/delivery/add
  * add Bearer in the header with the accessToken
    * Request : {
                  "method": "DRIVE",
                  "dateTime": "03/07/2024 09:15"
                  }