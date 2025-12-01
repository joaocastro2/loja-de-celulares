CREATE TABLE suppliers (
    supplier_id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    supplier_name TEXT NOT NULL,
    description TEXT,
    category VARCHAR(15),
    active BOOLEAN
);