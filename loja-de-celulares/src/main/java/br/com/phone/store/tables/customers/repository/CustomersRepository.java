package br.com.phone.store.tables.customers.repository;

import br.com.phone.store.tables.customers.model.CustomersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link CustomersModel} entities.
 *
 * <p>Extends {@link JpaRepository} to provide standard CRUD operations and
 * query capabilities for the {@code customers} table.</p>
 *
 * <p>Spring Data JPA automatically implements this interface, allowing
 * interaction with the database without the need for boilerplate code.</p>
 */
public interface CustomersRepository extends JpaRepository<CustomersModel, Integer> {

    List<CustomersModel> findAll();

}
