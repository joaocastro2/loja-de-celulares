CREATE TABLE customers(
    customer_id INT PRIMARY KEY UNIQUE NOT NULL SET DEFAULT nextval('customer_seq');
    customer_name TEXT NOT NULL,
    customer_ssn CHAR(9) NOT NULL,
    customer_email TEXT,
    customer_phone CHAR(10) NOT NULL
);