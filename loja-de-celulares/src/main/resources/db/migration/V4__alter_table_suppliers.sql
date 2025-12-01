ALTER TABLE suppliers
DROP COLUMN description;

ALTER TABLE suppliers
DROP COLUMN category;

ALTER TABLE suppliers
ADD COLUMN supplier_cpf CHAR(11) NOT NULL;