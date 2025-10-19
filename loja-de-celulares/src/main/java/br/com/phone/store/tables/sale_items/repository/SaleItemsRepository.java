package br.com.phone.store.tables.sale_items.repository;

import br.com.phone.store.tables.sale_items.model.SaleItemsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemsRepository extends JpaRepository <SaleItemsModel, Integer>{}