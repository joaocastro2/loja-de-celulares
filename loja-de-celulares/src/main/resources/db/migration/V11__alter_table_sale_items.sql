ALTER TABLE sale_items
ADD CONSTRAINT fk_product_id
FOREIGN KEY (product_id)
REFERENCES stock(product_id);

ALTER TABLE sale_items
ADD CONSTRAINT fk_sale_id
FOREIGN KEY (sale_id)
REFERENCES sales(sale_id);