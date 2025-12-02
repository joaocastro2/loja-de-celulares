CREATE TABLE customers(
    customer_id INTEGER PRIMARY KEY UNIQUE NOT NULL DEFAULT nextval('customer_seq'),
    customer_name TEXT NOT NULL,
    customer_cpf CHAR(11) NOT NULL,
    customer_email TEXT,
    customer_phone CHAR(10) NOT NULL
);