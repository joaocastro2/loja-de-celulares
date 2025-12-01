package br.com.phone.store.tables.suppliers.repository;

import br.com.phone.store.tables.suppliers.model.SuppliersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing {@link SuppliersModel} entities.
 *
 * <p>Extends {@link JpaRepository} to provide standard CRUD operations and
 * query capabilities for the {@code suppliers} table.</p>
 *
 * <p>Spring Data JPA automatically implements this interface, enabling
 * seamless interaction with the database without the need for boilerplate code.</p>
 */
public interface SuppliersRepository extends JpaRepository<SuppliersModel, Integer> {

    List<SuppliersModel> findAll();

}
