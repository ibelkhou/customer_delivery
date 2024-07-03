CREATE SEQUENCE IF NOT EXISTS ID_CUSTOMER_SEQ START WITH 1;
CREATE TABLE IF NOT EXISTS customer (
    id BIGINT NOT NULL DEFAULT nextval('ID_CUSTOMER_SEQ') PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(250) NOT NULL UNIQUE,
    password VARCHAR(250) NOT NULL,
    status VARCHAR(100) NOT NULL
);

INSERT INTO customer (firstname, lastname, email, password, status) values ('Ismail', 'BELKHOU', 'ibel@test.com', '$2a$12$WXs7Jj1mW05Xg13.aXpHbuDy9p2G5UYrVXKKMYSyAUL7ld309ZnFe', 'VALIDATED');

CREATE SEQUENCE IF NOT EXISTS ID_DELIVERY_SEQ START WITH 1;
CREATE TABLE IF NOT EXISTS delivery (
    id BIGINT NOT NULL DEFAULT nextval('ID_DELIVERY_SEQ') PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    delivery_method VARCHAR(100) NOT NULL,
    delivery_date TIMESTAMP NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    delivery_status VARCHAR(100) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);