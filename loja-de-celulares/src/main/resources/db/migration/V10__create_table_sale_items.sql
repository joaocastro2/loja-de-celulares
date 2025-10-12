CREATE TABLE sale_items(
    saleitems_id INT PRIMARY KEY UNIQUE NOT NULL DEFAULT nextval('saleitems_seq'),
    sale_id INT NOT NULL,
    product_id uuid NOT NULL,
    saleitems_qtty INT,
    unit_price DECIMAL(10,2) NOT NULL,
    saleitems_subtotal DECIMAL(10,2) NOT NULL
);