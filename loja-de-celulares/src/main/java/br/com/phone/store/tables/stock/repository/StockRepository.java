package br.com.phone.store.tables.stock.repository;

import br.com.phone.store.tables.stock.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing {@link StockModel} entities.
 *
 * <p>Extends {@link JpaRepository} to provide standard CRUD operations and
 * custom query methods for the {@code stock} table.</p>
 *
 * <p>Used primarily for search and filtering operations, which are implemented
 * and exposed through the controller layer.</p>
 */
public interface StockRepository extends JpaRepository<StockModel, UUID> {

    /**
     * Retrieves all products that are currently marked as active.
     *
     * @return List of active {@link StockModel} entities.
     */
    List<StockModel> findAllByActiveTrue();

    /**
     * Finds products by exact name match, ignoring case sensitivity.
     *
     * @param product_name The exact name of the product.
     * @return List of matching {@link StockModel} entities.
     */
    List<StockModel> findByProductNameIgnoreCase(String product_name);

    /**
     * Finds products by partial name match, ignoring case sensitivity.
     *
     * @param product_name Partial name or keyword to search for.
     * @return List of matching {@link StockModel} entities.
     */
    List<StockModel> findByProductNameContainingIgnoreCase(String product_name);
}
