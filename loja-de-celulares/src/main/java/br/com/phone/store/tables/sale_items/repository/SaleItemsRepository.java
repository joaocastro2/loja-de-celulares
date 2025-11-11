package br.com.phone.store.tables.sale_items.repository;

import br.com.phone.store.tables.sale_items.model.SaleItemsModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link SaleItemsModel} entities.
 *
 * <p>Extends {@link JpaRepository} to provide standard CRUD operations and
 * query capabilities for the {@code sale_items} table.</p>
 *
 * <p>Spring Data JPA automatically implements this interface, allowing
 * interaction with the database without the need for boilerplate code.</p>
 */
public interface SaleItemsRepository extends JpaRepository<SaleItemsModel, Integer> {}
