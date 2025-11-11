package br.com.phone.store.tables.users.repository;

import br.com.phone.store.tables.users.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing {@link UsersModel} entities.
 *
 * <p>Extends {@link JpaRepository} to provide standard CRUD operations and
 * query capabilities for the {@code users} table.</p>
 *
 * <p>Spring Data JPA automatically implements this interface, enabling
 * seamless interaction with the database without the need for boilerplate code.</p>
 */
public interface UsersRepository extends JpaRepository<UsersModel, Integer> {

    /**
     * Finds a user by their email address.
     *
     * @param userEmail The email address of the user.
     * @return An {@link Optional} containing the {@link UsersModel} if found, or empty if not.
     */
    Optional<UsersModel> findByUserEmail(String userEmail);
}
