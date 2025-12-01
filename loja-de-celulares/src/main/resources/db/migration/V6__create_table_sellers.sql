CREATE TABLE sellers(
    seller_id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    seller_name TEXT NOT NULL,
    seller_ssn CHAR(9) NOT NULL,
    seller_email TEXT,
    seller_comrate NUMERIC(5,2) DEFAULT 0.04,
    active BOOLEAN
);