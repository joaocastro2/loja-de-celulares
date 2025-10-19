package br.com.phone.store.tables.stock.repository;

import br.com.phone.store.tables.stock.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/*Inherits a series of ready-made methods that facilitate interaction with the database, such as CRUD operations (Create, Read, Update, Delete).
The rule defined in this business is to use it for custom functions, such as searches, for example, and implement them in the controller */

public interface StockRepository extends JpaRepository<StockModel, UUID> {

    List<StockModel> findAllByActiveTrue();
    List<StockModel> findByProductNameIgnoreCase(String product_name);
    List<StockModel> findByProductNameContainingIgnoreCase(String product_name);
}
