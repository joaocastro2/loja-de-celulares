ALTER TABLE stock
ADD COLUMN fk_supplier_id CHAR(9) NOT NULL;

ALTER TABLE stock
ADD CONSTRAINT fk_produto_fornecedor
FOREIGN KEY (fk_supplier_id) REFERENCES suppliers(supplier_id);