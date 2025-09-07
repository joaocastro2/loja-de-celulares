CREATE TABLE sales(
    sale_id INT PRIMARY KEY UNIQUE NOT NULL DEFAULT nextval('sales_seq'),
    customer_id INT NOT NULL,
    seller_id INT NOT NULL,
    sale_date DATE NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    CONSTRAINT fk_seller FOREIGN KEY (seller_id) REFERENCES sellers(seller_id)
);