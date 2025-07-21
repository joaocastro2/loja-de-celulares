ALTER TABLE suppliers
DROP COLUMN description;

ALTER TABLE suppliers
DROP COLUMN category;

ALTER TABLE suppliers
ADD COLUMN supplier_eiN CHAR(9) NOT NULL;