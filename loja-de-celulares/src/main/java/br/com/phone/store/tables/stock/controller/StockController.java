package br.com.phone.store.tables.stock.controller;

import br.com.phone.store.tables.stock.dto.RequestStockDto;
import br.com.phone.store.tables.stock.model.StockModel;
import br.com.phone.store.tables.stock.repository.StockRepository;
import br.com.phone.store.tables.suppliers.model.SuppliersModel;
import br.com.phone.store.tables.suppliers.repository.SuppliersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST controller responsible for managing product stock operations.
 *
 * <p>This controller provides endpoints for registering new products, retrieving products
 * by ID or name, and listing all active products. It also validates supplier information
 * during product registration.</p>
 *
 * <p>Cross-origin requests are allowed from the frontend running at http://localhost:5173.</p>
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private SuppliersRepository supplierRepository;

    /**
     * Registers a new product in the stock.
     *
     * <p>Validates the supplier ID, creates a {@link StockModel} from the provided DTO,
     * and persists it in the database.</p>
     *
     * @param newProduct DTO containing product and supplier details.
     * @return ResponseEntity with the saved product or error message.
     */
    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody @Valid RequestStockDto newProduct) {
        try {
            // Conversão de String para Integer no supplier_id
            SuppliersModel supplier = getSupplierOrThrow(Integer.valueOf(newProduct.supplier_id()));
            StockModel stockModel = new StockModel(newProduct, supplier);
            stockRepository.save(stockModel);
            return ResponseEntity.ok(stockModel);
        } catch (NumberFormatException e) {
            // Captura falhas na conversão de String para Integer
            return ResponseEntity.badRequest().body("supplier_id inválido ou malformado.");
        } catch (RuntimeException e) {
            // Captura erro de fornecedor não encontrado (lançado pelo getSupplierOrThrow)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Retrieves a product by its exact ID.
     *
     * @param product_id Product ID (Integer) used as identifier.
     * @return ResponseEntity with the matching product or 404 if none found.
     */
    @GetMapping("/id/{product_id}")
    // 🔑 CORREÇÃO: Altera o retorno para StockModel, pois ID é único
    public ResponseEntity<StockModel> findById(@PathVariable Integer product_id) {
        // 🔑 CORREÇÃO: stockRepository.findById(ID) retorna Optional<StockModel>
        Optional<StockModel> productById = stockRepository.findById(product_id);

        // Trata o Optional
        if (productById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Retorna a entidade StockModel desembrulhada
        return ResponseEntity.ok(productById.get());
    }

    /**
     * Lists all products currently marked as active.
     *
     * @return ResponseEntity with the list of active products.
     */
    @GetMapping
    public ResponseEntity<List<StockModel>> listAllActive() {
        List<StockModel> stockQuery = stockRepository.findAllByActiveTrue();
        return ResponseEntity.ok(stockQuery);
    }

    /**
     * Searches for products by name using both exact and partial matches (case-insensitive).
     *
     * @param product_name Name or partial name of the product.
     * @return ResponseEntity with matching products or 404 if none found.
     */
    @GetMapping("/name/{product_name}")
    public ResponseEntity<List<StockModel>> findByProductName(@PathVariable String product_name) {
        Set<StockModel> finalResult = new LinkedHashSet<>();
        finalResult.addAll(stockRepository.findByProductNameIgnoreCase(product_name));
        finalResult.addAll(stockRepository.findByProductNameContainingIgnoreCase(product_name));

        if (finalResult.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ArrayList<>(finalResult));
    }

    /**
     * Helper method to retrieve a supplier by ID or throw an exception if not found.
     *
     * @param supplierId Supplier INTEGER.
     * @return The {@link SuppliersModel} entity.
     * @throws RuntimeException if the supplier is not found.
     */
    private SuppliersModel getSupplierOrThrow(Integer supplierId) {
        // O helper está correto, usa findById que retorna Optional.
        return supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }
}