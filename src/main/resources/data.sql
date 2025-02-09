CREATE TABLE tb_produtos (

    id VARCHAR(36) NOT NULL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL UNIQUE,
    product_description VARCHAR(500) NOT NULL,
    product_price NUMERIC(10, 2) NOT NULL,
    product_quantity INTEGER NOT NULL

);