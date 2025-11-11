package br.com.phone.store.tables.sellers.repository;

import br.com.phone.store.tables.sellers.model.SellersModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link SellersModel} entities.
 *
 * <p>Extends {@link JpaRepository} to provide standard CRUD operations and
 * query capabilities for the {@code sellers} table.</p>
 *
 * <p>Spring Data JPA automatically implements this interface, enabling
 * seamless interaction with the database without the need for boilerplate code.</p>
 */
public interface SellersRepository extends JpaRepository<SellersModel, Integer> {}
