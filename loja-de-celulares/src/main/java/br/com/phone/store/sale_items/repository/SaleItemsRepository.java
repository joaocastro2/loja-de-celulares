package br.com.phone.store.sale_items.repository;

import br.com.phone.store.sale_items.model.SaleItemsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemsRepository extends JpaRepository <SaleItemsModel, Integer>{}