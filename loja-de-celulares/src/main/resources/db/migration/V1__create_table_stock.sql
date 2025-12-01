CREATE TABLE stock (
    product_id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    product_name TEXT NOT NULL,
    price_in_cents INT NOT NULL,
    amount INT DEFAULT 0,
    active BOOLEAN
);