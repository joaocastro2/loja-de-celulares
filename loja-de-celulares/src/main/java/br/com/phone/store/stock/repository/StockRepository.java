package br.com.phone.store.stock.repository;

import br.com.phone.store.stock.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*Inherits a series of ready-made methods that facilitate interaction with the database, such as CRUD operations (Create, Read, Update, Delete).
The rule defined in this business is to use it for custom functions, such as searches, for example, and implement them in the controller */

public interface StockRepository extends JpaRepository<StockModel, String> {
    List<StockModel> findAllByActiveTrue();

    List<StockModel> findProductByNameIgnoreCase(String product_name);
    List<StockModel> findProductByNameContainingIgnoreCase(String product_name);
}
