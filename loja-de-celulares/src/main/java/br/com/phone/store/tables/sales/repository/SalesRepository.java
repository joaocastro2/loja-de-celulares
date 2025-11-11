package br.com.phone.store.tables.sales.repository;

import br.com.phone.store.tables.sales.model.SalesModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link SalesModel} entities.
 *
 * <p>Extends {@link JpaRepository} to provide standard CRUD operations and
 * query capabilities for the {@code sales} table.</p>
 *
 * <p>Spring Data JPA automatically implements this interface, enabling
 * seamless interaction with the database without the need for boilerplate code.</p>
 */
public interface SalesRepository extends JpaRepository<SalesModel, Integer> {}
