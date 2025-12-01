ALTER TABLE suppliers
DROP COLUMN description;

ALTER TABLE suppliers
DROP COLUMN category;

ALTER TABLE suppliers
ADD COLUMN supplier_cnpj CHAR(14) NOT NULL;